package com.sivalabs.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.UnknownHostException;

@Component
public class TweetStreamListener {
    private final TweetStreamWorker tweetStreamWorker;

    @Autowired
    public TweetStreamListener(TweetStreamWorker tweetStreamWorker) {
        this.tweetStreamWorker = tweetStreamWorker;
    }

    @PostConstruct
    public void init() throws UnknownHostException {
        tweetStreamWorker.startTweetConsumer();
    }

    @PreDestroy
    public void destroy() {
        tweetStreamWorker.destroyTweetConsumer();
    }
}
