package com.sivalabs.springmongodbdemo;

import com.sivalabs.springmongodbdemo.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class SpringMongodbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMongodbDemoApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
