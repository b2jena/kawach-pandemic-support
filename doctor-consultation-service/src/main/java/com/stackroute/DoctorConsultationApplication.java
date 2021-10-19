package com.stackroute;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@EnableAutoConfiguration
@SpringBootApplication
public class DoctorConsultationApplication
{
    public static void main( String[] args )
    {

        SpringApplication.run(DoctorConsultationApplication.class, args);
    }
}
