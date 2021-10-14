package com.stackroute.resource.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {
    private RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqSender.class);

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${spring.rabbitmq.exchange1}")
    private String volunteerExchange;

    @Value("${spring.rabbitmq.routingkey1}")
    private String volunteerRoutingkey;

    public void send(String message) {
        System.out.println("data sent:"+message);
        rabbitTemplate.convertAndSend(exchange, routingkey, message);
    }

    public void sendVolunteer(String mail, String type) {
        String message = type+"&&"+mail;
        logger.info("data sent:"+message);
        rabbitTemplate.convertAndSend(volunteerExchange, volunteerRoutingkey, message);
    }
}
