package com.sivalabs.geeksclub.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/login").setViewName("login")
        registry.addViewController("/registration_status").setViewName("registration_status")
    }

    @Bean
    fun securityDialect(): SpringSecurityDialect {
        return SpringSecurityDialect()
    }
}