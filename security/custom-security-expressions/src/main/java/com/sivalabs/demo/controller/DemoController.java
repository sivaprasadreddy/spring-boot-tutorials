package com.sivalabs.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/ping")
    public String ping() {
        return "Pong";
    }

    @GetMapping("/mping")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public String moderatorPing() {
        return "Moderator Pong";
    }

    @GetMapping("/deletePost")
    @PreAuthorize("hasPrivilege('DELETE_POST')")
    public String deletePost() {
        return "Post deleted";
    }


}
