package com.stackroute.usermanagementservice.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UserAlreadyExistsException extends Exception {
    private String message;
}
