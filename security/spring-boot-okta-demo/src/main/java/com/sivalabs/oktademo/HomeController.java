package com.sivalabs.oktademo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    String home(@AuthenticationPrincipal Authentication authentication, Model model) {
        if (authentication != null) {
            List<String> authorities = authentication.getAuthorities()
                    .stream()
                    .map(Object::toString).toList();
            model.addAttribute("authorities", authorities);
        }
        return "home";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('SCOPE_profile')")
    public String userDetails(OAuth2AuthenticationToken authentication, Model model) {
        model.addAttribute("details", authentication.getPrincipal().getAttributes());
        return "userProfile";
    }
}