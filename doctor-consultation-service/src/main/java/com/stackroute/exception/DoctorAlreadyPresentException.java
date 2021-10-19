package com.stackroute.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DoctorAlreadyPresentException extends Exception{
    private String message;
}
