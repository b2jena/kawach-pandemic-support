package com.stackroute.service;

import com.stackroute.exception.NoEmailException;
import com.stackroute.exception.NoOTPException;
import com.stackroute.model.Patient;

import javax.mail.MessagingException;
import java.util.List;

/*This is the Patient Service class where abstract methods are declared*/
public interface PatientServiceI {
    /*This method will save the user into H2 database*/
    Patient saveUser(Patient patient) throws NoEmailException;
    /*This method will generate OTP and send it to the patient Email-Id*/
    Patient generateOtp(Patient patient) throws NoEmailException, MessagingException;
    /*This method will validate the OTP sent and the OTP that patient enters.*/
    String validateOtp(int otpNum) throws NoOTPException;
}
