package com.stackroute.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*This is a global expectation class which store the error message for NullValueException*/
@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message}")
    public String message;

    /*This method is responsible for throwing the nullValueException*/
    @ExceptionHandler(value = NullValueException.class)
    public ResponseEntity<String> nullValueException(NullValueException nullValueException) {
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
