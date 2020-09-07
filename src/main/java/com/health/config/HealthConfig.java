package com.health.config;

import com.health.indicator.ServiceHealth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class HealthConfig {

    @Autowired
    private ServiceHealth serviceHealth;

    @Bean
    public ReactiveHealthContributor coreServices() {
        return CompositeReactiveHealthContributor.fromMap(Map.of("serviceHealthContrib", serviceHealth));
    }

    @Autowired
    private HealthAggregator healthAggregator;

    @Bean
    public ReactiveHealthIndicator coreServices2() {
        ReactiveHealthIndicatorRegistry registry = new DefaultReactiveHealthIndicatorRegistry(new LinkedHashMap<>());
        registry.register("serviceHealthRegistry", serviceHealth);
        return new CompositeReactiveHealthIndicator(healthAggregator, registry);
    }


}
