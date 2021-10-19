package com.stackroute.usermanagementservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*RabbitMq Sender class to send the messages via RabbitMq*/
@Service
public class RabbitMqSender {

    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    Logger logger = LoggerFactory.getLogger(RabbitMqSender.class.getName());

    private RabbitTemplate rabbitTemplate;

    /*Rabbit Template is injected using @Autowired annotation*/
    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${spring.rabbitmq.exchange1}")
    private String doctorExchange;

    @Value("${spring.rabbitmq.routingkey1}")
    private String doctorRoutingkey;

    /*Method to send the login credentials of Doctors and Volunteers to user authentication service*/
    public void send(String loginDetails) throws Exception {
        try {
            rabbitTemplate.convertAndSend(exchange, routingkey, loginDetails);
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            throw new Exception();
        }

    }

    /*Method to send a Doctor's details to the patient dashboard*/
    public void sendDoctor(String onlineDetails) throws Exception {
        try {
            rabbitTemplate.convertAndSend(doctorExchange, doctorRoutingkey, onlineDetails);
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            throw new Exception();
        }
    }
}
