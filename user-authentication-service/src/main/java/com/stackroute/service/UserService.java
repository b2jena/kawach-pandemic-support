package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserNotFoundException;

public interface UserService {

    User saveUser(User user);

    User findByIdAndPassword(String id, String password) throws UserNotFoundException;
}
