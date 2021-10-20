package com.stackroute.usermanagementservice.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/*This is a UserAlreadyExistsException class which extends the Exception class*/
@NoArgsConstructor
@AllArgsConstructor
public class UserAlreadyExistsException extends Exception {
    private String message;
}
