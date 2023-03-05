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
                .route(r -> r.path("/api/v1/doctor/**").uri("http://localhost:8989/"))
                .route(r -> r.path("/api/v1/information/**").uri("http://localhost:9765/"))
                .route(r -> r.path("/api/v1/otp/**").uri("http://localhost:9999/"))
                .route(r -> r.path("/api/v1/resource/**").uri("http://localhost:9901/"))
                .route(r -> r.path("/api/v1/login/**").uri("http://localhost:9099/"))
                .route(r -> r.path("/api/v1/user/**").uri("http://localhost:8090/"))
                .route(r -> r.path("/api/v1/volunteer/**").uri("http://localhost:8686/"))
                .route(r -> r.path("/api/v1/chat-messages/**").uri("http://localhost:8095/"))
                //mention all the routes of all the services
                .build();
    }

}
