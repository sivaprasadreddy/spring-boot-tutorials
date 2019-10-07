package com.sivalabs.myservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sivalabs.myservice.config.ApplicationProperties;
import com.sivalabs.myservice.model.GithubUser;
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

    @HystrixCommand(fallbackMethod = "getDefaultUser", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public GithubUser getGithubUserProfile(String username) {
        log.info("GithubBaseUrl:"+properties.getGithubBaseUrl());
        return this.restTemplate.getForObject(properties.getGithubBaseUrl() + "/users/" + username, GithubUser.class);
    }

    GithubUser getDefaultUser(String username) {
        log.info("---------getDefaultUser-----------");
        GithubUser user = new GithubUser();
        user.setId(-1L);
        user.setLogin("guest");
        user.setName("Guest");
        user.setPublicRepos(0);
        return user;
    }
}
