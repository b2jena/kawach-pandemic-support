package com.stackroute.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.logging.Logger;
@SpringBootApplication
public class AddResourceServiceApplication {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(AddResourceServiceApplication.class.getName());
        SpringApplication.run(AddResourceServiceApplication.class, args);
        logger.info("AddResourceServiceApplication Server is running");
    }
}
