package com.houseofpizza.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean(name = "AuditorAware")
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication()).map(authentication -> {
            String username = authentication.getName();
            if ("anonymousUser".equals(username)) {
                return appName;
            }
            return username;
        }).or(() -> Optional.ofNullable(appName));
    }
}

