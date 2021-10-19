package com.stackroute.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorGeneratingOTP extends Exception{
    private String message;
}
