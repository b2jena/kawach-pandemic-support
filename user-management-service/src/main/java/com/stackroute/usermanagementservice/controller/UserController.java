package com.stackroute.usermanagementservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.usermanagementservice.exception.NullValueException;
import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Doctor;
import com.stackroute.usermanagementservice.model.Volunteer;
import com.stackroute.usermanagementservice.service.DoctorService;
import com.stackroute.usermanagementservice.service.RabbitMqSender;
import com.stackroute.usermanagementservice.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/")
public class UserController {
    private DoctorService doctorService;
    private VolunteerService volunteerService;
    private RabbitMqSender rabbitMqSender;
    @Value("${data.controller.doctorTag}")
    public String doctorTag;
    @Value("${data.controller.volunteerTag}")
    public String volunteerTag;
    @Value("${data.controller.userGotSaved}")
    public String userGotSaved;


    @Autowired
    public UserController(DoctorService doctorService, VolunteerService volunteerService, RabbitMqSender rabbitMqSender) {
        this.doctorService = doctorService;
        this.volunteerService = volunteerService;
        this.rabbitMqSender = rabbitMqSender;

    }

    @PostMapping("register/doctor")
    public ResponseEntity<String> saveDoctors(@RequestParam(value = "image") MultipartFile image,
                                              @RequestParam("item") String item) throws IOException, UserAlreadyExistsException, NullValueException {
        Doctor doctor = new ObjectMapper().readValue(item, Doctor.class);
        doctor.setImageByte(image.getBytes());
        doctor.setImageName(image.getOriginalFilename());
        doctor.setType(image.getContentType());
        doctorService.saveDoctor(doctor);
        String doctorLoginDetails = doctor.getEmailId() + ", " + doctor.getPassword() + ", " + doctorTag;
        String doctorOnlineDetails = doctor.getEmailId() + ", " + doctor.getFullName() + ", " + doctor.getSpecialization();
        rabbitMqSender.send(doctorLoginDetails);
        rabbitMqSender.sendDoctor(doctorOnlineDetails);
        return new ResponseEntity<>(userGotSaved, HttpStatus.CREATED);
    }

    @PostMapping("register/volunteer")
    public ResponseEntity<String> saveVolunteers(@RequestBody Volunteer volunteer) throws UserAlreadyExistsException, NullValueException {
        volunteerService.saveVolunteer(volunteer);
        String volunteerLoginDetails = volunteer.getEmailId() + ", " + volunteer.getPassword() + ", " + volunteerTag;
        rabbitMqSender.send(volunteerLoginDetails);
        return new ResponseEntity<>(userGotSaved, HttpStatus.CREATED);
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable("id") String id) {
        return new ResponseEntity<Doctor>(doctorService.getDoctorById(id), HttpStatus.OK);
    }


}
