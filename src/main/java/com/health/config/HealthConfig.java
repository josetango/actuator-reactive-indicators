package com.health.config;

import com.health.indicator.ServiceHealth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeReactiveHealthContributor;
import org.springframework.boot.actuate.health.ReactiveHealthContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class HealthConfig {

    @Autowired
    private ServiceHealth serviceHealth;

    @Bean
    public ReactiveHealthContributor coreServices() {
        return CompositeReactiveHealthContributor.fromMap(Map.of("serviceHealthContrib", serviceHealth));
    }
}