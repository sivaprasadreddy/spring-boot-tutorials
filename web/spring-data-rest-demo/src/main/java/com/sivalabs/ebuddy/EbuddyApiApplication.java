package com.sivalabs.ebuddy;

import com.sivalabs.ebuddy.config.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfiguration.class)
public class EbuddyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbuddyApiApplication.class, args);
    }
}
