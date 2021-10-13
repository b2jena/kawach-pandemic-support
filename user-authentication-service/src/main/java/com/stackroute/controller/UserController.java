package com.stackroute.controller;

import com.stackroute.config.JWTTokenGenerator;
import com.stackroute.domain.User;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;
    private JWTTokenGenerator jwtTokenGenerator;
    ResponseEntity<?> responseEntity;

    @Autowired
    public UserController(UserService userService, JWTTokenGenerator jwtTokenGenerator) {
        this.userService = userService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @Value("${app.controller.exception.message1}")
    private String message1;

    @Value("${app.controller.exception.message2}")
    private String message2;

    @Value("${app.controller.exception.message3}")
    private String message3;





    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("login/user")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> json) {
        try {
            if (json.get("id") == null || json.get("password") == null) {
                throw new UserNotFoundException(message1);
            }
            User userDetails = userService.findByIdAndPassword(json.get("id"), json.get("password"));
            if (userDetails == null) {
                throw new UserNotFoundException(message2);
            }
            if (!(userDetails.getPassword().equals(userDetails.getPassword()))) {
                throw new UserNotFoundException(message3);
            }
            List<String> response=new ArrayList<String>();
            response.add("Success");
            response.add(jwtTokenGenerator.generateToken(userDetails));
            response.add(userDetails.getRole().toString());
            response.add(userDetails.getId().toString());
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            List<String> response=new ArrayList<String>();
            response.add(e.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        }
        return responseEntity;
    }


}
