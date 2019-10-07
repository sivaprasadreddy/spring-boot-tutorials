package com.sivalabs.myservice.web.controllers;

import com.sivalabs.myservice.model.GithubUser;
import com.sivalabs.myservice.services.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/github")
public class GithubController {

    private final GithubService githubService;

    @Autowired
    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/users/{username}")
    public GithubUser getGithubUserProfile(@PathVariable String username) {
        return githubService.getGithubUserProfile(username);
    }
}
