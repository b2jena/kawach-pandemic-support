package com.stackroute.userrevertservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*Rabbit Mq configuration class*/
@Configuration
public class RabbitMQConfig {

    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class.getName());


    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.host}")
    private String host;


    /*Method bean declaring the queue*/
    @Bean
    Queue queue() {
        return new Queue(queue, true);
    }

    /*Method bean declaring the Exchange*/
    @Bean
    Exchange myExchange() {
        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }

    /*Method bean binding the queue, exchange and the routing key*/
    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(myExchange()).with(routingKey).noargs();
    }

    /*Method to establish the connection*/
    @Bean
    ConnectionFactory connectionFactory() throws Exception {
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

    /*Method to convert messages into the JSON format*/
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /*Method to convert the data into the JSON format*/
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
