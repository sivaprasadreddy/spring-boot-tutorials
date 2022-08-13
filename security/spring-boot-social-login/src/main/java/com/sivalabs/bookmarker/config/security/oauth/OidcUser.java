package com.sivalabs.bookmarker.config.security.oauth;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

public class OidcUser extends DefaultOidcUser implements AuthenticatedPrincipal {

    public OidcUser(org.springframework.security.oauth2.core.oidc.user.OidcUser user) {
        super(user.getAuthorities(), user.getIdToken(), "name");
    }

    @Override
    public String getId() {
        return super.getClaimAsString("id");
    }

    @Override
    public String getImageUrl() {
        return super.getPicture();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }
}
