package com.sivalabs.bookmarker.config.security.oauth;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.sivalabs.bookmarker.domain.utils.Constants.ROLE_USER;

@Setter
public class GithubOAuth2User extends LinkedHashMap<String, Object> implements OAuth2User, AuthenticatedPrincipal {

    private List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(ROLE_USER);
    private Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        if (this.attributes == null) {
            this.attributes = this;
        }
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getId() {
        return String.valueOf(this.getAttributes().get("id"));
    }

    @Override
    public String getEmail() {
        return String.valueOf(this.getAttributes().get("email"));
    }

    @Override
    public String getImageUrl() {
        return String.valueOf(this.getAttributes().get("avatar_url"));
    }

    @Override
    public String getName() {
        return String.valueOf(this.getAttributes().get("name"));
    }
}
