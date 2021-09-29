package com.stackroute.resource.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message}")
    public String message;

    @ExceptionHandler(value = NullValueException.class)
    public ResponseEntity<String> nullValueException(NullValueException nullValueException) {
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
