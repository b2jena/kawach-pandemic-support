package com.stackroute.usermanagementservice.controller;

import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Doctor;
import com.stackroute.usermanagementservice.model.Volunteer;
import com.stackroute.usermanagementservice.service.DoctorService;
import com.stackroute.usermanagementservice.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Controller {
    private DoctorService doctorService;
    private VolunteerService volunteerService;


    @Autowired
    public Controller(DoctorService doctorService, VolunteerService volunteerService) {
        this.doctorService = doctorService;
        this.volunteerService = volunteerService;

    }

    @PostMapping("/doc")
    public ResponseEntity<Doctor> saveDoc(@RequestBody Doctor doctor) throws UserAlreadyExistsException {
        Doctor savedDoc = doctorService.saveDoc(doctor);
        return new ResponseEntity<>(savedDoc, HttpStatus.CREATED);
    }

    @PostMapping("/vol")
    public ResponseEntity<Volunteer> saveVol(@RequestBody Volunteer volunteer) throws UserAlreadyExistsException{
        Volunteer savedVol = volunteerService.saveVolunteer(volunteer);
        return new ResponseEntity<>(savedVol, HttpStatus.CREATED);
    }



}
