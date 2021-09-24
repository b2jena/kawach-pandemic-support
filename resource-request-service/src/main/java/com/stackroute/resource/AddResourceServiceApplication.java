package com.stackroute.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddResourceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AddResourceServiceApplication.class, args);
        System.out.println("Server is running");
    }
}
