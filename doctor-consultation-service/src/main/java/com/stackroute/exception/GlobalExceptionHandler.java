package com.stackroute.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

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

    /*This method is responsible for throwing the DoctorAlreadyPresentException*/
    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<String> noEmailException(DoctorAlreadyPresentException doctorAlreadyPresentException) {
        return new ResponseEntity<String>(message1, HttpStatus.OK);
    }

    /*This method is responsible for throwing the DoctorAlreadyPresentException*/
    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<String> doctorNotFoundException(DoctorNotFoundException doctorNotFoundException) {
        return new ResponseEntity<String>(message2, HttpStatus.OK);
    }

    /*This method is responsible for throwing the DatabaseEmptyException*/
    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity<String> databaseEmptyException(DatabaseEmptyException databaseEmptyException) {
        return new ResponseEntity<String>(message3, HttpStatus.OK);
    }
}
