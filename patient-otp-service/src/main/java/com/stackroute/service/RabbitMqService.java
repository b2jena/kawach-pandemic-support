package com.stackroute.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements RabbitListenerConfigurer {

    private EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqService.class);

    @Autowired
    public RabbitMqService(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void recievedMessage(String message) {

        String[] parts=message.split("&&");
        logger.info("Recieved details part 0: " + parts[0]);
        logger.info("Recieved details part 1: " + parts[1]);

        try {
            emailService.sendOtpMessage(parts[1], "Close of SOS request", parts[0]);
        } catch (Exception e) {
            logger.error("Sending of message failed");
        }

    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
