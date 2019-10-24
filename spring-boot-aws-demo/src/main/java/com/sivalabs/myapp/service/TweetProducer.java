package com.sivalabs.myapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@Slf4j
public class TweetProducer {

    private final KinesisStreamService kinesisStreamService;

    private String tweetStreamName = "tweet_stream";

    @Autowired
    public TweetProducer(KinesisStreamService kinesisStreamService) {
        this.kinesisStreamService = kinesisStreamService;
    }

    @PostConstruct
    void init() throws Exception {
        kinesisStreamService.createStream(tweetStreamName, 1);
    }

    @Scheduled(fixedRate = 20000)
    public void generateTweets() {
        String tweet = "Random tweet - "+ UUID.randomUUID().toString();
        log.info("Sending tweet to stream: "+tweet);
        kinesisStreamService.putData(tweetStreamName, "1234", tweet);
    }
}
