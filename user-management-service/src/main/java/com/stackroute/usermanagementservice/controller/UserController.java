package com.stackroute.usermanagementservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.usermanagementservice.exception.NullValueException;
import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Doctor;
import com.stackroute.usermanagementservice.model.Volunteer;
import com.stackroute.usermanagementservice.service.DoctorService;
import com.stackroute.usermanagementservice.service.RabbitMqSender;
import com.stackroute.usermanagementservice.service.VolunteerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/*This is a controller class containing Api of saving and fetching doctor and volunteer registration details into mongoDB database
 * This class is annotated with @RestController, @CrossOrigin and @RequestMapping annotation*/

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/v1/user/")
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

    /*Doctor Service, Volunteer Service and Rabbit Mq Sender is injected in this controller class by @Autowired annotation*/
    @Autowired
    public UserController(DoctorService doctorService, VolunteerService volunteerService, RabbitMqSender rabbitMqSender) {
        this.doctorService = doctorService;
        this.volunteerService = volunteerService;
        this.rabbitMqSender = rabbitMqSender;

    }

    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /*This Post Mapping method is responsible for saving the Doctor registration details in the mongoDB repository*/
    @PostMapping("register/doctor")
    public ResponseEntity<String> saveDoctors(@RequestParam(value = "image") MultipartFile image,
                                              @RequestParam("item") String item) throws Exception {
        try {
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
        } catch (IOException e) {
            logger.error("Failed to save Doctor registration details: IO exception");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (UserAlreadyExistsException e) {
            logger.error("Failed to save Doctor registration details: User already Exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (NullValueException e) {
            logger.error("Failed to save Doctor registration details: Null value");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /*This Post Mapping method is responsible for saving the Volunteer registration details in the mongoDB repository*/
    @PostMapping("register/volunteer")
    public ResponseEntity<String> saveVolunteers(@RequestBody Volunteer volunteer) throws Exception {
        try {
            volunteerService.saveVolunteer(volunteer);
            String volunteerLoginDetails = volunteer.getEmailId() + ", " + volunteer.getPassword() + ", " + volunteerTag;
            rabbitMqSender.send(volunteerLoginDetails);
            return new ResponseEntity<>(userGotSaved, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            logger.error("Failed to save Volunteer registration details: User Already Exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (NullValueException e) {
            logger.error("Failed to save volunteer registration details: Null value");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /*This Post Mapping method is responsible for getting the Doctor registration details by email ID from the mongoDB repository*/
    @GetMapping("/doctor/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<Doctor>(doctorService.getDoctorById(id), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Failed to save retrieve Doctor's details");
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

    }


}
