package com.stackroute.resource.exception;

public class ResourceAlreadyExistsException extends Exception{
    private String message;
    public ResourceAlreadyExistsException(){

    }
    public ResourceAlreadyExistsException(String message){
        super();
        this.message = message;
    }
}
