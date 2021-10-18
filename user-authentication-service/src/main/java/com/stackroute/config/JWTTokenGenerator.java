package com.stackroute.config;


import com.stackroute.domain.User;

/*This class declares the abstract method generateToken*/
public interface JWTTokenGenerator {

    String generateToken(User user);
}
