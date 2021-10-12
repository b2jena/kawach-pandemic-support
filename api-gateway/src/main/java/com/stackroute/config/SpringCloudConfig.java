package com.stackroute.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("chat-service", r -> r.path("/api/v1/**")
                        .uri("http://localhost:8095/"))

                .route("doctor-consultation-service", r -> r.path("/api/v1/**")
                        .uri("http://localhost:8989/"))

                .route("information-service", r -> r.path("/api/v1/**")
                        .uri("http://localhost:9765/"))
                
                .route("patient-otp-service", r -> r.path("/api/v1/**")
                        .uri("http://localhost:9999/"))

                .route("resource-request-service", r -> r.path("/api/v1/**")
                        .uri("http://localhost:9901/"))

                .route("user-authentication-service", r -> r.path("/api/v1/**")
                        .uri("http://localhost:9099/"))

                .route("user-management-service", r -> r.path("/api/v1/**")
                        .uri("http://localhost:8090/"))

                .route("volunteer-revert-service", r -> r.path("/api/v1/**")
                        .uri("http://localhost:8686/"))
                .build();
    }

}
