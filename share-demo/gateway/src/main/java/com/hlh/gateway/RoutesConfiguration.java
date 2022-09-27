package com.hlh.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class RoutesConfiguration {
    @Bean
    public RouteLocator declare(RouteLocatorBuilder builder){
        return builder.routes().route(route -> route.path("/api/shares/**")
                .filters(f -> f.stripPrefix(1))
                .uri("lb://content-service"))
                .route(route -> route.path("/api/notices/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://content-service")).build();
    }
}
