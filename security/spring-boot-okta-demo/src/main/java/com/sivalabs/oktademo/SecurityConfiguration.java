package com.sivalabs.oktademo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        LogoutSuccessHandler handler = new SimpleUrlLogoutSuccessHandler();
        return handler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().mvcMatchers("/").permitAll().and().anonymous()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().oauth2Client()
                .and().oauth2Login()
                .and().logout().logoutSuccessHandler(logoutSuccessHandler());

        return http.build();
    }
}
