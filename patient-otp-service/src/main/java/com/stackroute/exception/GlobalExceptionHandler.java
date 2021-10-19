package com.stackroute.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*This is a global expectation class which store the error message for NullValueException*/
@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message1}")
    public String message1;

    @Value(value = "${data.exception.message2}")
    public String message2;

    @Value(value = "${data.exception.message3}")
    public String message3;

    @Value(value = "${data.exception.message4}")
    public String message4;

    /*This method is responsible for throwing the noEmailException*/
    @ExceptionHandler(value = ClassNotFoundException.class)
    public ResponseEntity<String> noEmailException(NoEmailException noEmailException) {
        return new ResponseEntity<String>(message1, HttpStatus.OK);
    }

    /*This method is responsible for throwing the noOTPException*/
    @ExceptionHandler(value = ClassNotFoundException.class)
    public ResponseEntity<String> noOTPException(NoOTPException noOTPException) {
        return new ResponseEntity<String>(message2, HttpStatus.OK);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> errorSendingEmail(ErrorSendingEmail errorSendingEmail) {
        return new ResponseEntity<String>(message3, HttpStatus.OK);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> errorGeneratingOTP(ErrorGeneratingOTP errorGeneratingOTP) {
        return new ResponseEntity<String>(message4, HttpStatus.OK);
    }
}
