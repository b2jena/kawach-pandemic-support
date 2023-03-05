package com.stackroute.userrevertservice.service;

import com.stackroute.userrevertservice.model.VolunteerIncoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService implements RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqService.class);
    private VolunteerRevertService volunteerRevertService;

    @Autowired
    public RabbitMqService(VolunteerRevertService volunteerRevertService) {
        this.volunteerRevertService = volunteerRevertService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void recievedMessage(String message) {

        String[] parts = message.split("&&");
        logger.info("Received details: " + parts[0] + ", " + parts[1]);
        VolunteerIncoming volunteerIncoming = new VolunteerIncoming(parts[1], parts[0]);
//        volunteerIncoming.setEmailId(parts[1]);
//        volunteerIncoming.setType(parts[0]);
        volunteerRevertService.volunteerRevertUpdate(volunteerIncoming);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
