package com.sivalabs.myapp.service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.clientlibrary.interfaces.v2.IRecordProcessorFactory;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.KinesisClientLibConfiguration;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.Worker;
import com.amazonaws.services.kinesis.metrics.interfaces.MetricsLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

@Component
@Slf4j
public class TweetStreamWorker {

    private static final String SAMPLE_APPLICATION_NAME = "AWS_LOCALSTACK_DEMO";
    private static final String SAMPLE_APPLICATION_STREAM_NAME = "tweet_stream";
    // Initial position in the stream when the application starts up for the first time.
    // Position can be one of LATEST (most recent data) or TRIM_HORIZON (oldest available data)
    private static final InitialPositionInStream SAMPLE_APPLICATION_INITIAL_POSITION_IN_STREAM =
            InitialPositionInStream.LATEST;

    @Autowired
    private AmazonKinesis amazonKinesis;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private AmazonCloudWatch amazonCloudWatch;

    private Worker worker;

    @Async
    public void startTweetConsumer() throws UnknownHostException {
        String workerId = InetAddress.getLocalHost().getCanonicalHostName() + ":" + UUID.randomUUID();

        KinesisClientLibConfiguration kinesisClientLibConfiguration =
                new KinesisClientLibConfiguration(SAMPLE_APPLICATION_NAME,
                        SAMPLE_APPLICATION_STREAM_NAME,
                        new DefaultAWSCredentialsProviderChain(),
                        workerId);
        kinesisClientLibConfiguration.withInitialPositionInStream(SAMPLE_APPLICATION_INITIAL_POSITION_IN_STREAM);
        kinesisClientLibConfiguration.withMetricsLevel(MetricsLevel.NONE);

        IRecordProcessorFactory recordProcessorFactory = new TweetRecordProcessorFactory();

        this.worker = new Worker.Builder()
                .recordProcessorFactory(recordProcessorFactory)
                .config(kinesisClientLibConfiguration)
                .dynamoDBClient(amazonDynamoDB)
                .kinesisClient(amazonKinesis)
                .cloudWatchClient(amazonCloudWatch)
                .build();

        log.info("Running {} to process stream {} as worker {}...\n",
                SAMPLE_APPLICATION_NAME,
                SAMPLE_APPLICATION_STREAM_NAME,
                workerId);

        this.worker.run();
    }

    public void destroyTweetConsumer() {
        this.worker.shutdown();
    }
}
