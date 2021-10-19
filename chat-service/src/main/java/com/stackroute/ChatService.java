package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

/*This is the main application class annotated with @Spring Boot Application annotation*/
@SpringBootApplication
public class ChatService {
    public static void main(String[] args) {
        SpringApplication.run(ChatService.class, args);
        Logger logger = Logger.getLogger(ChatService.class.getName());
        logger.info("Server Started Successfully");
    }
}
