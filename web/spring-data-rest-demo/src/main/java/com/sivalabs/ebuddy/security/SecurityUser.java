package com.sivalabs.ebuddy.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.stream.Collectors;

public class SecurityUser extends User {
    private com.sivalabs.ebuddy.entity.User user;

    public SecurityUser(com.sivalabs.ebuddy.entity.User user) {
        super(user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList()));
        this.user = user;
    }

    public com.sivalabs.ebuddy.entity.User getUser() {
        return user;
    }
}
