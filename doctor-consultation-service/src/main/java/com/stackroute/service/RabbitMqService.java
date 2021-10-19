package com.stackroute.service;

import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;
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
    private DoctorService doctorService;
    private Doctor doctor;

    @Autowired
    public RabbitMqService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void recievedMessage(String userDetails) throws DoctorNotFoundException {
        String[] parts=userDetails.split(", ");
        doctor=new Doctor(parts[0],parts[1],parts[2]);
        doctorService.saveDoctorMongoDB(doctor);
        logger.info("Received user details: " + parts[0] + ", "+parts[1] +", "+ parts[2]);

    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
