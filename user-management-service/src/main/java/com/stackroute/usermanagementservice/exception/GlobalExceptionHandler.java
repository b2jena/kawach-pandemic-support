package com.stackroute.usermanagementservice.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message1}")
    public String message1;
    @Value(value = "${data.exception.message2}")
    public String message2;

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<String> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
        return new ResponseEntity<String>(message1, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NullValueException.class)
    public ResponseEntity<String> nullValueException(NullValueException nullValueException) {
        return new ResponseEntity<String>(message2, HttpStatus.CONFLICT);
    }
}
