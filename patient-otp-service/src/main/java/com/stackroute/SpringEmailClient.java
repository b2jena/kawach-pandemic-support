package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class SpringEmailClient {
    public static void main(String[] args) {
        SpringApplication.run(SpringEmailClient.class, args);
        System.out.println("Client is running");
    }

}