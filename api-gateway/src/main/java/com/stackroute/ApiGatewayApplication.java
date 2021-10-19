package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*This is a Spring Boot Application class for bootstraping the Spring Cloud Gateway 
 * This class is annotated with @SpringBootApplication annotation*/

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}