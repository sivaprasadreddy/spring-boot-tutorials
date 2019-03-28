package com.sivalabs.ebuddy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ebuddy")
@Data
public class AppConfiguration {
    private String logstashHost = "localhost";
    private JwtConfig jwt = new JwtConfig();

    @Data
    public static class JwtConfig {
        private String header = "";
        private String issuer = "sivalabs";
        private Long expiresIn = 604800L;
        private String secret = "secret";
    }
}
