package com.sivalabs.sbidemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannel greetingsChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow flow1() {
        return IntegrationFlows
                .from("greetingsChannel")
                //.transform("payload.toLowerCase()")
                .transform(new ToUpperCaseConverter())
                .handle(new LoggingHandler("INFO"))
                .get();
    }
}

@MessagingGateway(defaultRequestChannel = "greetingsChannel")
interface MyService {
    void send(String msg);
}

class ToUpperCaseConverter implements GenericTransformer<String, String> {

    @Override
    public String transform(String s) {
        return "UPPERCASED:"+s.toUpperCase();
    }
}
