package com.stackroute.config;


import com.stackroute.domain.User;

public interface JWTTokenGenerator {

    String generateToken(User user);
}
