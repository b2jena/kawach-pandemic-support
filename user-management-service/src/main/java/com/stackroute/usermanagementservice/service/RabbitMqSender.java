package com.stackroute.usermanagementservice.service;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {
    private RabbitTemplate rabbitTemplate;
    private RabbitTemplate rabbitTemplate1;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate, RabbitTemplate rabbitTemplate1) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate1 = rabbitTemplate1;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public void send(String loginDetails) {
        rabbitTemplate.convertAndSend(exchange, routingkey, loginDetails);
    }

    @Value("${spring.rabbitmq.exchange1}")
    private String doctorExchange;

    @Value("${spring.rabbitmq.routingkey1}")
    private String doctorRoutingkey;

    @Value("${spring.rabbitmq.queue1}")
    private String queue1;


    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(doctorExchange);
    }

    @Bean
    public Queue queue() {
        return new Queue(queue1);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with(doctorRoutingkey);
    }

    public void sendDoctor(String onlineDetails) {
        rabbitTemplate1.convertAndSend(doctorExchange, doctorRoutingkey, onlineDetails);
    }
}
