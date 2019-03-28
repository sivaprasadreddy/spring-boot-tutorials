package com.sivalabs.authserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(-5) // prior to AuthorizationServerSecurityConfiguration (order = 0)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .requestMatchers()
            .antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
            .and()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll();
    }
}
