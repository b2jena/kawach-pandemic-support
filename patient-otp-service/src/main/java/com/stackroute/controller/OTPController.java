package com.stackroute.controller;

import java.util.HashMap;
import java.util.Map;

import com.stackroute.service.EmailService;
import com.stackroute.service.EmailTemplate;
import com.stackroute.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;


@Controller
public class OTPController {


    @Autowired
    public OTPService otpService;

    @Autowired
    public EmailService emailService;

    @GetMapping("/generateOtp")
    public String generateOTP() throws MessagingException {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = "godwinkhalko2@gmail.com";
        int otp = otpService.generateOTP("godwinkhalko2@gmail.com");
        //Generate The Template to send OTP
        EmailTemplate template = new EmailTemplate("src/main/java/com/stackroute/SendOTP.html");
        Map<String,String> replacements = new HashMap<String,String>();
        replacements.put("user", username);
        replacements.put("otpnum", String.valueOf(otp));
        String message = template.getTemplate(replacements);
        emailService.sendOtpMessage("godwinkhalko2@gmail.com", "OTP -SpringBoot", message);

        return "otppage";
    }

    @RequestMapping(value ="/validateOtp", method = RequestMethod.GET)
    public @ResponseBody String validateOtp(@RequestParam("otpnum") int otpnum){

        final String SUCCESS = "Entered Otp is valid";
        final String FAIL = "Entered Otp is NOT valid. Please Retry!";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        //Validate the Otp
        if(otpnum >= 0){

            int serverOtp = otpService.getOtp(username);
            if(serverOtp > 0){
                if(otpnum == serverOtp){
                    otpService.clearOTP(username);

                    return (SUCCESS);
                }
                else {
                    return FAIL;
                }
            }else {
                return FAIL;
            }
        }else {
            return FAIL;
        }
    }
}