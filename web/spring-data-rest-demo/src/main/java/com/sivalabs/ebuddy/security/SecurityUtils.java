package com.sivalabs.ebuddy.security;

import com.sivalabs.ebuddy.entity.User;
import com.sivalabs.ebuddy.repo.UserRepository;
import com.sivalabs.ebuddy.security.auth.TokenBasedAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    private UserRepository userRepository;

    @Autowired
    public SecurityUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof TokenBasedAuthentication) {
            TokenBasedAuthentication tokenBasedAuthentication = (TokenBasedAuthentication) authentication;
            SecurityUser user = (SecurityUser) tokenBasedAuthentication.getPrincipal();
            return userRepository.findByEmail(user.getUsername());
        }
        return null;
    }
}
