package com.sivalabs.bookmarker.config.security.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.stereotype.Service;

@Service("oidcUserService")
@RequiredArgsConstructor
public class CustomOidcUserService extends OidcUserService {

    @Override
    public org.springframework.security.oauth2.core.oidc.user.OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        org.springframework.security.oauth2.core.oidc.user.OidcUser oidcUser = super.loadUser(userRequest);
        return new OidcUser(oidcUser);
    }
}
