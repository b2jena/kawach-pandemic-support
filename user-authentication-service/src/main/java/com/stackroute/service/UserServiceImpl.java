package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    @Value("${app.service.message2}")
    private String message2;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User findByIdAndPassword(String id, String password) throws UserNotFoundException {
        User authUser = userRepository.findByIdAndPassword(id, password);
        if (authUser == null) {
            throw new UserNotFoundException(message2);
        }
        return authUser;
    }

}
