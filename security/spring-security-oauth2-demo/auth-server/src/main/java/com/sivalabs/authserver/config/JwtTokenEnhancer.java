package com.sivalabs.authserver.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additional = new HashMap<>();
        additional.put("org", "sivalabs");
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken)accessToken;
        token.setAdditionalInformation(additional);
        return accessToken;
    }
}
