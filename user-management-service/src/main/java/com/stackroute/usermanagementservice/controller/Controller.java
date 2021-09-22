package com.stackroute.usermanagementservice.controller;

import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Doctor;
import com.stackroute.usermanagementservice.model.Volunteer;
import com.stackroute.usermanagementservice.service.DoctorService;
import com.stackroute.usermanagementservice.service.RabbitMqSender;
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
    private RabbitMqSender rabbitMqSender;



    @Autowired
    public Controller(DoctorService doctorService, VolunteerService volunteerService, RabbitMqSender rabbitMqSender) {
        this.doctorService = doctorService;
        this.volunteerService = volunteerService;
        this.rabbitMqSender = rabbitMqSender;

    }

    @PostMapping("/doc")
    public ResponseEntity<Doctor> saveDoc(@RequestBody Doctor doctor) throws UserAlreadyExistsException {
        Doctor savedDoc = doctorService.saveDoc(doctor);
        String details = doctor.getEmailId()+", "+doctor.getPassword()+", Doctor";
        rabbitMqSender.send(details);
        return new ResponseEntity<>(savedDoc, HttpStatus.CREATED);
    }

    @PostMapping("/vol")
    public ResponseEntity<Volunteer> saveVol(@RequestBody Volunteer volunteer) throws UserAlreadyExistsException{
        Volunteer savedVol = volunteerService.saveVolunteer(volunteer);
        String details1 = volunteer.getEmailId()+", "+volunteer.getPassword()+", Volunteer";
        rabbitMqSender.send(details1);
        return new ResponseEntity<>(savedVol, HttpStatus.CREATED);
    }



}
