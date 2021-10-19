package com.stackroute.service;

import com.stackroute.exception.NoEmailException;
import com.stackroute.exception.NoOTPException;
import com.stackroute.model.Patient;
import com.stackroute.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*This is the implementation class of Patient Service where 
abstract methods of Patient Service are implemented*/
@Service
public class PatientServiceImpl implements PatientServiceI{
    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class.getName());

    private PatientRepository patientRepository;
    private OTPService otpService;
    private EmailService emailService;
    static String email;

    /*Patient Repository is injected in this Patient Service Implementation
        class by @Autowired annotation*/
    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, OTPService otpService, EmailService emailService) {
        this.patientRepository = patientRepository;
        this.otpService = otpService;
        this.emailService = emailService;
    }

    /*This method is responsible for saving users in the H2 databse.*/
    @Override
    public Patient saveUser(Patient patient) throws NoEmailException {
        try {
            return patientRepository.save(patient);
        }
        catch(Exception e)
        {
            logger.error(String.valueOf(e));
            throw  new NoEmailException();
        }
    }
    /*The method responsible for generating the OTP and sending it to patient.*/
    @Override
    public Patient generateOtp(Patient patient) throws NoEmailException, MessagingException {
        try{
            Patient patient1 = saveUser(patient);
            email = patient1.getStrEmail();
            int otp = otpService.generateOTP(email);
            Map<String, String> replacements = new HashMap<String, String>();
            replacements.put("user", email);
            replacements.put("otpnum", String.valueOf(otp));
            emailService.sendOtpMessage(email, "OTP -SpringBoot", String.valueOf(otp));
            return patient;
        }
        catch(MessagingException e)
        {
            logger.error("Error in sending email.");
            throw new MessagingException();
        }
        catch(Exception e)
        {
            logger.error("Error in sending email.");
            throw new NoEmailException();
        }
    }
    /*This method is responsible for validating the OTP.*/
    @Override
    public String validateOtp(int otpNum) throws NoOTPException {
        try{
            final String SUCCESS = "SUCCESS";
            final String FAIL = "FAIL";
            if (otpNum >= 0) {
                int serverOtp = otpService.getOtp(email);
                if (serverOtp > 0) {
                    if (otpNum == serverOtp) {
                        otpService.clearOTP(email);
                        return SUCCESS;
                    } else {
                        return FAIL;
                    }
                } else {

                    return FAIL;
                }
            } else {
                return FAIL;
            }
        }
        catch(Exception e)
        {
            logger.error("Error in sending email.");
            throw new NoOTPException();
        }
    }
}

