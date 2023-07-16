package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("inTest",
                        route -> route.path("/api/user/**")
                                .uri("http://localhost:8081"))
                .route("pet",
                        route -> route.path("/api/pet/**")
                                .uri("http://localhost:8082")
                ).build();

    }
}
