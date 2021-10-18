package com.stackroute.exception;

/*This is an exception class to throw exception if a user is not found in the database*/
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
    }
}
