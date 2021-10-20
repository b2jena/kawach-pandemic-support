package com.stackroute.controller;

import com.stackroute.exception.NoEmailException;
import com.stackroute.exception.NoOTPException;
import com.stackroute.model.Patient;
import com.stackroute.service.PatientServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/otp/")
@CrossOrigin(value="*")
public class OTPController {
    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    Logger logger = LoggerFactory.getLogger(OTPController.class.getName());

    public PatientServiceI patientServiceI;

    /*We have autowired the patientServiceI*/
    @Autowired
    public OTPController(PatientServiceI patientServiceI) {
        this.patientServiceI = patientServiceI;
    }
    /*Method responsible to generate OTP*/
    @PostMapping("/generateOtp")
    public ResponseEntity<?> generateOTP(@RequestBody Patient patient){
        try{
            Patient patient1 = patientServiceI.generateOtp(patient);
            return new ResponseEntity<Patient>(patient, HttpStatus.OK);
        }
        catch(Exception exception)
        {
            logger.error("Failed to send OTP");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /*Method responsible to Validate the OTP*/

    @GetMapping("/validateOtp/{otpNum}")
    public ResponseEntity<String> validateOtp(@PathVariable int otpNum){
        try{
            String Message = patientServiceI.validateOtp(otpNum);
            return new ResponseEntity<String>(Message, HttpStatus.OK);
        }
        catch(Exception exception){
            logger.error("Failed to validate OTP");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}