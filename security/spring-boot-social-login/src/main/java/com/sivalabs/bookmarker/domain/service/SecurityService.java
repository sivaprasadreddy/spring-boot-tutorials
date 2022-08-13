package com.sivalabs.bookmarker.domain.service;

import com.sivalabs.bookmarker.config.security.SecurityUser;
import com.sivalabs.bookmarker.config.security.oauth.AuthenticatedPrincipal;
import com.sivalabs.bookmarker.domain.entity.User;
import com.sivalabs.bookmarker.domain.model.BookmarkDTO;
import com.sivalabs.bookmarker.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SecurityService {
    private final UserRepository userRepository;

    public User loginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof SecurityUser) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            return securityUser.getUser();
        } else if(authentication instanceof OAuth2AuthenticationToken) {
            Object principal = authentication.getPrincipal();
            if(principal instanceof AuthenticatedPrincipal) {
                String email = ((AuthenticatedPrincipal) principal).getEmail();
                return userRepository.findByEmail(email).orElse(null);
            }
        }
        return null;
    }

    public Long loginUserId() {
        User loginUser = loginUser();
        if(loginUser != null) {
            return loginUser.getId();
        }
        return null;
    }

}
