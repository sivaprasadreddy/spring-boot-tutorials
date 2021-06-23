package com.sivalabs.springmongodbdemo.services;

import com.sivalabs.springmongodbdemo.config.ApplicationProperties;
import com.sivalabs.springmongodbdemo.models.GithubUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class GithubService {
    private final ApplicationProperties properties;
    private final RestTemplate restTemplate;

    @Autowired
    public GithubService(ApplicationProperties properties, RestTemplate restTemplate) {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }

    public GithubUser getGithubUserProfile(String username) {
        log.info("GithubBaseUrl:"+properties.getGithubBaseUrl());
        return this.restTemplate.getForObject(properties.getGithubBaseUrl() + "/users/" + username, GithubUser.class);
    }

}