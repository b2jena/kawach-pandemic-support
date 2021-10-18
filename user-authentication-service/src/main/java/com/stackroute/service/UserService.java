package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserNotFoundException;

/*This is the User Service class where abstract methods are declared*/
public interface UserService {

    /*This method is to store the user in the database*/
    User saveUser(User user);

    /*This method is to find the user by the id and password*/
    User findByIdAndPassword(String id, String password) throws UserNotFoundException;
}
