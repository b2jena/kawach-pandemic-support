package com.stackroute.service;

import com.stackroute.domain.Role;
import com.stackroute.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*Service layer to receive message via rabbitmq
* */
@Component
public class RabbitMqService implements RabbitListenerConfigurer {

    /*This is to create a logger object to implement functions of the Logger class*/
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqService.class);

    private UserService userService;
    private User user2;

    @Autowired
    public RabbitMqService(UserService userService) {
        this.userService = userService;
    }

    /*Method to receive user details from User Management Service and
    store them in the database
    * */
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void recievedMessage(String userDetails) {

        String[] parts=userDetails.split(", ");
        if(parts[2].subSequence(1,parts[2].length()-1).equals("Doctor")){
            user2=new User(parts[0],parts[1],Role.Doctor);
            userService.saveUser(user2);
        }
        else if(parts[2].subSequence(1,parts[2].length()-1).equals("Volunteer")){
            user2=new User(parts[0],parts[1],Role.Volunteer);
            userService.saveUser(user2);
        }
        logger.info("Recieved user details: " + parts[0] + ", "+parts[1] +", "
        + parts[2].subSequence(1,parts[2].length()-1));

    }

    /*configureRabbitListeners method is overridden
    * */
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
