package com.stackroute.usermanagementservice.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {
    private RabbitTemplate rabbitTemplate;
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;
    @Value("${spring.rabbitmq.exchange1}")
    private String doctorExchange;
    @Value("${spring.rabbitmq.routingkey1}")
    private String doctorRoutingkey;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String loginDetails) {
        rabbitTemplate.convertAndSend(exchange, routingkey, loginDetails);
    }

    public void sendDoctor(String onlineDetails) {
        rabbitTemplate.convertAndSend(doctorExchange, doctorRoutingkey, onlineDetails);
    }
}
