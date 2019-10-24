package com.sivalabs.myapp.service;

import com.sivalabs.myapp.AbstractIntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KinesisStreamServiceIT extends AbstractIntegrationTest {

    @Autowired
    KinesisStreamService kinesisStreamService;

    private String streamName = "test-stream-1";

    private Integer streamSize = 1;

    @Before
    public void setUp() throws Exception {
        kinesisStreamService.createStream(streamName, streamSize);
    }

    @After
    public void tearDown() {
        kinesisStreamService.deleteStream(streamName);
    }

    @Test
    public void shouldListStreams() {
        List<String> streams = kinesisStreamService.listStreams();
        assertThat(streams).isNotEmpty();
        assertThat(streams).contains(streamName);

    }

    @Test
    public void shouldSendDataToStream() {
        long createTime = System.currentTimeMillis();
        String data = String.format("testData-%d", createTime);
        String partitionKey = String.format("partitionKey-%d", createTime);
        kinesisStreamService.putData(streamName, partitionKey, data);
    }
}
