package com.sivalabs.myservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GithubUser {
    private Long id;
    private String login;
    private String url;
    private String name;
    @JsonProperty("public_repos")
    private int publicRepos;
    private int followers;
    private int following;
}
