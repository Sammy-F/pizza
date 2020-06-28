package com.pizza.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * From: https://github.com/njnareshjoshi/articles/blob/master/spring-data-jpa-auditing/src/main/java/org/programming/mitra/JpaConfig.java
 */

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
class PersistenceConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}