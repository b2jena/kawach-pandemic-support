package com.stackroute.userrevertservice.controller;


import com.stackroute.userrevertservice.model.Volunteer;
import com.stackroute.userrevertservice.service.VolunteerRevertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*This is a controller class containing Api of fetching volunteer revert details from mongoDB database
 * This class is annotated with @RestController, @CrossOrigin and @RequestMapping annotation*/
@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/volunteer/")
public class VolunteerRevertController {

    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    private static final Logger logger = LoggerFactory.getLogger(VolunteerRevertController.class);

    private VolunteerRevertService volunteerRevertService;

    @Value("${data.controller.successMessage}")
    private String successMessage;

    /*Volunteer revert service is injected in this controller class by @Autowired annotation*/
    @Autowired
    public VolunteerRevertController(VolunteerRevertService volunteerRevertService) {
        this.volunteerRevertService = volunteerRevertService;
    }


    /*This Get Mapping method is responsible for getting the Volunteer revert details from the mongoDB repository*/
    @GetMapping("volunteers")
    public ResponseEntity<List<Volunteer>> allVolunteers() throws Exception {
        try {
            return new ResponseEntity<List<Volunteer>>(volunteerRevertService.getAllVolunteers(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Failed to save retrieve the list of volunteer details");
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
}