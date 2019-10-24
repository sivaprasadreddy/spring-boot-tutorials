package com.sivalabs.sbidemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootIntegrationDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootIntegrationDemoApplication.class, args);
    }

    @Autowired
    MessageChannel greetingsChannel;

    @Autowired
    MyService myService;

    @Override
    public void run(String... args) {
        greetingsChannel.send(MessageBuilder.withPayload("Hello1").build());
        greetingsChannel.send(MessageBuilder.withPayload("Hello2").build());
        greetingsChannel.send(MessageBuilder.withPayload("Hello3").build());

        myService.send("How are you?");
    }
}
