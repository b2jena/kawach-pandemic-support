package com.stackroute.userrevertservice.service;

import com.stackroute.userrevertservice.model.VolunteerIncoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*RabbitMq class to fetch the volunteer action details from the queue of resource request service*/
@Service
public class RabbitMqService implements RabbitListenerConfigurer {

    private VolunteerRevertService volunteerRevertService;

    /*Volunteer Revert Service is injected in this class by @Autowired annotation*/
    @Autowired
    public RabbitMqService(VolunteerRevertService volunteerRevertService) {
        this.volunteerRevertService = volunteerRevertService;
    }

    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqService.class);


    /*Method to retrieve messages from the rabbit mq queue*/
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void recievedMessage(String message) throws Exception {
        try {
            String[] parts = message.split("&&");
            logger.info("Received details: " + parts[0] + ", " + parts[1]);
            VolunteerIncoming volunteerIncoming = new VolunteerIncoming(parts[1], parts[0]);
            volunteerRevertService.volunteerRevertUpdate(volunteerIncoming);
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            throw new Exception();
        }
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
