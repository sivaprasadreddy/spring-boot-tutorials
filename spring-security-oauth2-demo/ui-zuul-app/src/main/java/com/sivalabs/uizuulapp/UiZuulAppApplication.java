package com.sivalabs.uizuulapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

@EnableZuulProxy
@EnableOAuth2Sso
@SpringBootApplication
public class UiZuulAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(UiZuulAppApplication.class, args);
	}

	@Bean
	public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ClientContext oAuth2ClientContext, OAuth2ProtectedResourceDetails resourceDetails) {
		OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceDetails, oAuth2ClientContext);
		return oAuth2RestTemplate;
	}
}
