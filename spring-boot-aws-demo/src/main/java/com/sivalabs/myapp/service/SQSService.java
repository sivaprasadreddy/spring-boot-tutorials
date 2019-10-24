package com.sivalabs.myapp.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SQSService {
    private final AmazonSQS amazonSQS;

    @Autowired
    public SQSService(AmazonSQS amazonSQS) {
        this.amazonSQS = amazonSQS;
    }

    public void createQueue(String queueName) {
        CreateQueueRequest create_request = new CreateQueueRequest(queueName)
                .addAttributesEntry("DelaySeconds", "60")
                .addAttributesEntry("MessageRetentionPeriod", "86400");

        try {
            amazonSQS.createQueue(create_request);
        } catch (AmazonSQSException e) {
            if (!e.getErrorCode().equals("QueueAlreadyExists")) {
                throw e;
            }
        }
    }

    public ListQueuesResult listQueues() {
        return amazonSQS.listQueues();
    }

    public void sendMessage(String queueName, String msg) {
        String queue_url = amazonSQS.getQueueUrl(queueName).getQueueUrl();

        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queue_url)
                .withMessageBody(msg)
                .withDelaySeconds(5);
        amazonSQS.sendMessage(send_msg_request);
    }

    public List<Message> readMessages(String queueName) {
        String queue_url = amazonSQS.getQueueUrl(queueName).getQueueUrl();

        List<Message> messages = amazonSQS.receiveMessage(queue_url).getMessages();
        /*
        for (Message m : messages) {
            System.err.println("msg: "+m.getBody());
            amazonSQS.deleteMessage(queue_url, m.getReceiptHandle());
        }*/
        return messages;
    }

    public void deleteQueue(String queueName) {
        String queue_url = amazonSQS.getQueueUrl(queueName).getQueueUrl();
        amazonSQS.deleteQueue(queue_url);
    }

}
