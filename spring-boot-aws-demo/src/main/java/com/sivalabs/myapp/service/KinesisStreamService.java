package com.sivalabs.myapp.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.CreateStreamRequest;
import com.amazonaws.services.kinesis.model.DescribeStreamRequest;
import com.amazonaws.services.kinesis.model.DescribeStreamResult;
import com.amazonaws.services.kinesis.model.ListStreamsRequest;
import com.amazonaws.services.kinesis.model.ListStreamsResult;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordResult;
import com.amazonaws.services.kinesis.model.ResourceNotFoundException;
import com.amazonaws.services.kinesis.model.StreamDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class KinesisStreamService {

    private final AmazonKinesis amazonKinesis;

    @Autowired
    public KinesisStreamService(AmazonKinesis amazonKinesis) {
        this.amazonKinesis = amazonKinesis;
    }

    public void createStream(String streamName, Integer streamSize) throws Exception {
        // Describe the stream and check if it exists.
        DescribeStreamRequest describeStreamRequest = new DescribeStreamRequest().withStreamName(streamName);
        try {
            System.out.println(amazonKinesis.describeStream(describeStreamRequest));
            StreamDescription streamDescription = amazonKinesis.describeStream(describeStreamRequest).getStreamDescription();
            System.out.printf("Stream %s has a status of %s.\n", streamName, streamDescription.getStreamStatus());

            if ("DELETING".equals(streamDescription.getStreamStatus())) {
                //throw new RuntimeException("Stream is being deleted");
                System.out.printf("Stream %s is being deleted. Creating it now.\n", streamName);
                Thread.sleep(TimeUnit.SECONDS.toMillis(10));
                createStreamInternal(streamName, streamSize);
            }

            // Wait for the stream to become active if it is not yet ACTIVE.
            if (!"ACTIVE".equals(streamDescription.getStreamStatus())) {
                waitForStreamToBecomeAvailable(streamName);
            }
        } catch (ResourceNotFoundException ex) {
            System.out.printf("Stream %s does not exist. Creating it now.\n", streamName);
            createStreamInternal(streamName, streamSize);
        }
    }

    private void createStreamInternal(String streamName, Integer streamSize) throws InterruptedException {
        // Create a stream. The number of shards determines the provisioned throughput.
        CreateStreamRequest createStreamRequest = new CreateStreamRequest();
        createStreamRequest.setStreamName(streamName);
        createStreamRequest.setShardCount(streamSize);
        amazonKinesis.createStream(createStreamRequest);
        // The stream is now being created. Wait for it to become active.
        waitForStreamToBecomeAvailable(streamName);
    }

    public List<String> listStreams() {
        ListStreamsRequest listStreamsRequest = new ListStreamsRequest();
        listStreamsRequest.setLimit(10);
        ListStreamsResult listStreamsResult = amazonKinesis.listStreams(listStreamsRequest);
        List<String> streamNames = listStreamsResult.getStreamNames();
        while (listStreamsResult.isHasMoreStreams()) {
            if (streamNames.size() > 0) {
                listStreamsRequest.setExclusiveStartStreamName(streamNames.get(streamNames.size() - 1));
            }

            listStreamsResult = amazonKinesis.listStreams(listStreamsRequest);
            streamNames.addAll(listStreamsResult.getStreamNames());
        }
        return streamNames;
    }

    public void putData(String streamName, String partitionKey, String data) {
        PutRecordRequest putRecordRequest = new PutRecordRequest();
        putRecordRequest.setStreamName(streamName);
        putRecordRequest.setData(ByteBuffer.wrap(data.getBytes()));
        putRecordRequest.setPartitionKey(partitionKey);
        PutRecordResult putRecordResult = amazonKinesis.putRecord(putRecordRequest);
        System.out.printf("Successfully put record, partition key : %s, ShardID : %s, SequenceNumber : %s.\n",
                putRecordRequest.getPartitionKey(),
                putRecordResult.getShardId(),
                putRecordResult.getSequenceNumber());
    }

    private void waitForStreamToBecomeAvailable(String streamName) throws InterruptedException {
        System.out.printf("Waiting for %s to become ACTIVE...\n", streamName);

        long startTime = System.currentTimeMillis();
        long endTime = startTime + TimeUnit.MINUTES.toMillis(20);
        while (System.currentTimeMillis() < endTime) {
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));

            try {
                DescribeStreamRequest describeStreamRequest = new DescribeStreamRequest();
                describeStreamRequest.setStreamName(streamName);
                // ask for no more than 10 shards at a time -- this is an optional parameter
                describeStreamRequest.setLimit(10);
                DescribeStreamResult describeStreamResponse = amazonKinesis.describeStream(describeStreamRequest);

                String streamStatus = describeStreamResponse.getStreamDescription().getStreamStatus();
                System.out.printf("\t- current state: %s\n", streamStatus);
                if ("ACTIVE".equals(streamStatus)) {
                    return;
                }
            } catch (ResourceNotFoundException ex) {
                // ResourceNotFound means the stream doesn't exist yet,
                // so ignore this error and just keep polling.
            } catch (AmazonServiceException ase) {
                throw ase;
            }
        }

        throw new RuntimeException(String.format("Stream %s never became active", streamName));
    }

    public void deleteStream(String streamName) {
        amazonKinesis.deleteStream(streamName);
    }
}
