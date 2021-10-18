package com.stackroute.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/*This is a NullValueException class which extends the Exception class*/
@NoArgsConstructor
@AllArgsConstructor
public class NullValueException extends Exception {
    private String message;
}
