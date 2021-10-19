package com.stackroute.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ErrorSendingEmail extends Exception{
    private String message;
}
