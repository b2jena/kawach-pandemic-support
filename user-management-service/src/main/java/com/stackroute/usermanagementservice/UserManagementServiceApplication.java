package com.stackroute.usermanagementservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/*This is the main application class annotated with @SpringBootApplication annotation*/
@SpringBootApplication
public class UserManagementServiceApplication {

    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    Logger logger = LoggerFactory.getLogger(UserManagementServiceApplication.class.getName());

    /*Fetching the data from the application.properties file*/
    @Value("${spring.rabbitmq.host}")
    String host;

    @Value("${spring.rabbitmq.username}")
    String username;

    @Value("${spring.rabbitmq.password}")
    String password;

    public static void main(String[] args) {
        SpringApplication.run(UserManagementServiceApplication.class, args);
    }

    /*Method to establish a connection*/

    @Bean
    CachingConnectionFactory connectionFactory() throws Exception {
        try {
            CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
            cachingConnectionFactory.setUsername(username);
            cachingConnectionFactory.setPassword(password);
            return cachingConnectionFactory;
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            throw new Exception();
        }
    }


    /*Message converter method to convert a message into JSON format*/
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() throws Exception {
        try {
            return new Jackson2JsonMessageConverter();
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            throw new Exception();
        }

    }

    /*Method to Convert the data into JSON format*/

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) throws Exception {
        try {
            final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setMessageConverter(jsonMessageConverter());
            return rabbitTemplate;
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            throw new Exception();
        }
    }
}
