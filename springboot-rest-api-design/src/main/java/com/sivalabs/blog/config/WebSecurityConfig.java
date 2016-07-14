/**
 * 
 */
package com.sivalabs.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import com.sivalabs.blog.security.HttpAuthenticationEntryPoint;
import com.sivalabs.blog.security.TokenProvider;
import com.sivalabs.blog.security.XAuthTokenConfigurer;

/**
 * @author Siva
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ROLE_ADMIN = "ADMIN";
    
    @Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;
        
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private TokenProvider tokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/templates/**")
            .antMatchers("/assets/**")
            .antMatchers("/webjars/**")
            .antMatchers("/resources/**")
            .antMatchers("/swagger-ui/index.html")
            ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
        .and()
            .csrf()
            .disable()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests()
            .antMatchers("/api/authenticate", "/api/loginUser").permitAll()
            .antMatchers(HttpMethod.POST,"/api/posts").hasRole(ROLE_ADMIN)
	        .antMatchers(HttpMethod.DELETE,"/api/posts").hasRole(ROLE_ADMIN)
	        .antMatchers("/api/comments","/api/comments/**").hasRole(ROLE_ADMIN)
        .and()
            .apply(securityConfigurerAdapter());

    }

    private XAuthTokenConfigurer securityConfigurerAdapter() {
      return new XAuthTokenConfigurer(userDetailsService, tokenProvider);
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
    
    
   
}

