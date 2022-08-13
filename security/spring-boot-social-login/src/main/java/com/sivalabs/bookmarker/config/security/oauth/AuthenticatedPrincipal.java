package com.sivalabs.bookmarker.config.security.oauth;

public interface AuthenticatedPrincipal {
    String getId();
    String getName();
    String getEmail();
    String getImageUrl();
}
