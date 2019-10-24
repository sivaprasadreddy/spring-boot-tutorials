package com.sivalabs.myapp.service;

import com.amazonaws.services.sqs.model.Message;
import com.sivalabs.myapp.AbstractIntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SQSServiceIT extends AbstractIntegrationTest {

    @Autowired
    SQSService sqsService;

    String queueName = "queue-1";

    @Before
    public void setUp() throws Exception {
        sqsService.createQueue(queueName);
    }

    @After
    public void tearDown() throws Exception {
        sqsService.deleteQueue(queueName);
    }

    @Test
    public void shouldSendMessageToQueue() throws InterruptedException {
        sqsService.sendMessage(queueName, "Hello");
        Thread.sleep(5000);
        List<Message> messages = sqsService.readMessages(queueName);
        assertThat(messages).hasSize(1);
        assertThat(messages.get(0).getBody()).isEqualTo("Hello");

    }
}
