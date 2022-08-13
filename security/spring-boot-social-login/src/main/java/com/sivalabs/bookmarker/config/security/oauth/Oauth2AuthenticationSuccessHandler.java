package com.sivalabs.bookmarker.config.security.oauth;

import com.sivalabs.bookmarker.domain.entity.User;
import com.sivalabs.bookmarker.domain.entity.UserType;
import com.sivalabs.bookmarker.domain.model.UserDTO;
import com.sivalabs.bookmarker.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

@Component("oauth2authSuccessHandler")
@RequiredArgsConstructor
@Slf4j
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String provider = UserType.LOCAL.name();
        if(authentication instanceof OAuth2AuthenticationToken) {
            provider = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
        }

        Object principal = authentication.getPrincipal();
        if(principal instanceof AuthenticatedPrincipal) {
            this.createUserIfNotExists(provider, (AuthenticatedPrincipal) principal);
        }
        this.redirectStrategy.sendRedirect(request, response, "/");
    }

    private void createUserIfNotExists(String provider, AuthenticatedPrincipal socialUser) {
        if(userService.isUserExistsByEmail(socialUser.getEmail())) {
            log.debug("User already registered with email: {}", socialUser.getEmail());
            return;
        }
        UserType userType = UserType.valueOf(provider.toUpperCase());
        User user = new User();
        user.setEmail(socialUser.getEmail());
        user.setImageUrl(socialUser.getImageUrl());
        user.setName(socialUser.getName());
        user.setUserType(userType);
        user.setRoles(new HashSet<>());
        user.setPassword("secret");
        userService.createUser(UserDTO.fromEntity(user));
    }
}
