package com.stackroute.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/v1/**")
                        .uri("http://localhost:8095/")
                        .id("chat-service"))

                .route(r -> r.path("/api/v1/**")
                        .uri("http://localhost:8989/")
                        .id("doctor-consultation-service"))

                .route(r -> r.path("/api/v1/**")
                        .uri("http://localhost:9765/")
                        .id("information-service"))
                
                .route(r -> r.path("/api/v1/**")
                        .uri("http://localhost:9999/")
                        .id("patient-otp-service"))

                .route(r -> r.path("/api/v1/**")
                        .uri("http://localhost:9901/")
                        .id("resource-request-service"))

                .route(r -> r.path("/api/v1/**")
                        .uri("http://localhost:9099/")
                        .id("user-authentication-service"))

                .route(r -> r.path("/api/v1/**")
                        .uri("http://localhost:8090/")
                        .id("user-management-service"))

                .route(r -> r.path("/api/v1/**")
                        .uri("http://localhost:8686/")
                        .id("volunteer-revert-service"))
                .build();
    }

}
