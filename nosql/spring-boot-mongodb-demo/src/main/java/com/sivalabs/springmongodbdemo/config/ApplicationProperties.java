package com.sivalabs.springmongodbdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "myapp")
public class ApplicationProperties {
    private String githubBaseUrl;
}
