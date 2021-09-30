package com.stackroute.controller;

import com.stackroute.entity.Patient;
import com.stackroute.exception.NoEmailException;
import com.stackroute.service.EmailService;
import com.stackroute.service.OTPService;
import com.stackroute.service.PatientServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class OTPController {

    public PatientServiceI patientServiceI;

    @Autowired
    public OTPController(PatientServiceI patientServiceI) {
        this.patientServiceI = patientServiceI;
    }

    @Autowired
    public OTPService otpService;

    @Autowired
    public EmailService emailService;

    @PostMapping(path = "/patient")
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) throws NoEmailException {
        Patient patient1 = patientServiceI.saveUser(patient);
        if (patient1.getEmail() == null) {
            throw new NoEmailException("Please enter email");
        } else {
            System.out.println(patient1.getEmail());
            return new ResponseEntity<Patient>(patient1, HttpStatus.OK);
        }
    }

    static String email;

    @PostMapping("/generateOtp")
    public ResponseEntity<?> generateOTP(@RequestBody Patient patient) throws MessagingException, NoEmailException {
        Patient patient1 = patientServiceI.saveUser(patient);
        if (patient1.getEmail() == null) {
            throw new NoEmailException("Please enter email");
        }
        email = patient1.getEmail();
        int otp = otpService.generateOTP(email);
        Map<String, String> replacements = new HashMap<String, String>();
        replacements.put("user", email);
        replacements.put("otpnum", String.valueOf(otp));
        emailService.sendOtpMessage(email, "OTP -SpringBoot", String.valueOf(otp));
        System.out.println("Mail sent");
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/validateOtp/{otpNum}")
    public ResponseEntity<String> validateOtp(@PathVariable int otpNum) {
        final String SUCCESS = "SUCCESS";
        final String FAIL = "FAIL";
        //Validate the Otp
        if (otpNum >= 0) {

            int serverOtp = otpService.getOtp(email);
            if (serverOtp > 0) {
                if (otpNum == serverOtp) {
                    otpService.clearOTP(email);
                    System.out.println("correct");
                    return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
                } else {
                    System.out.println("incorrect");
                    return new ResponseEntity<String>(FAIL, HttpStatus.OK);
                }
            } else {
                System.out.println("incorrect");

                return new ResponseEntity<String>(FAIL, HttpStatus.OK);
            }
        } else {
            System.out.println("incorrect");
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);
        }
    }

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllUser() {
        return new ResponseEntity<List<Patient>>((List<Patient>) patientServiceI.getAll(), HttpStatus.OK);

    }
}