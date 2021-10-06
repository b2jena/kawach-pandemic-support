package com.stackroute.exception;

public class DoctorAlreadyPresentException extends Exception{
    public DoctorAlreadyPresentException(String message) {
        super(message);
    }
    public DoctorAlreadyPresentException() {
    }
}
