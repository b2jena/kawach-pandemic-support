package com.stackroute.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class NoOTPException extends Exception{
    private String message;
}
