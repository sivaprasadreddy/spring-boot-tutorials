package com.sivalabs.bookmarker.config.security;

import com.sivalabs.bookmarker.config.security.oauth.AuthenticatedPrincipal;
import com.sivalabs.bookmarker.domain.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.stream.Collectors;

public class SecurityUser extends org.springframework.security.core.userdetails.User implements AuthenticatedPrincipal {
    private User user;

    public SecurityUser(User user) {
        super(user.getEmail(), user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getId() {
        return String.valueOf(this.user.getId());
    }

    @Override
    public String getName() {
        return this.user.getName();
    }

    @Override
    public String getEmail() {
        return this.user.getEmail();
    }

    @Override
    public String getImageUrl() {
        return this.user.getImageUrl();
    }
}
