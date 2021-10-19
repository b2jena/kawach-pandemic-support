package com.stackroute.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.stackroute.controller.OTPController;
import com.stackroute.exception.ErrorSendingEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


/*This is the Email service class wherein we define the logic to send Emails*/
@Service
public class EmailService {
    Logger logger = LoggerFactory.getLogger(EmailService.class.getName());
    private JavaMailSender javaMailSender;

    /*we have autowired or injected the predefined class named JavaMailSender
    which contains the method to send the mail.*/
    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /*Message to configure the the email to be sent and then send it to the recipient.*/
    public void sendOtpMessage(String to, String subject, String message) throws MessagingException, ErrorSendingEmail {
        try{
            MimeMessage msg = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(msg, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message, true);
            javaMailSender.send(msg);
        }
        catch(Exception e)
        {
            logger.error("Error in sending email.");
            throw new ErrorSendingEmail();
        }
    }
}