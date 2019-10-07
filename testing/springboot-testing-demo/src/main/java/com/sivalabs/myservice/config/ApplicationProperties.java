package com.sivalabs.myservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApplicationProperties {

    @Value("${githuub.api.base-url}")
    private String githubBaseUrl;
}
