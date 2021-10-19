package com.stackroute.controller;

import com.stackroute.exception.DatabaseEmptyException;
import com.stackroute.exception.DoctorAlreadyPresentException;
import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;
import com.stackroute.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/* This is a controller class containing Api of saving,
 * fetching and deleting the Doctors from mongoDB and Redis database
 * This class is annotated with @RestController, @CrossOrigin and @RequestMapping annotation*/
@RestController
@CrossOrigin(value="*")
@RequestMapping("/api/v1/")
public class DoctorController {
    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    Logger logger = LoggerFactory.getLogger(DoctorController.class.getName());
    private DoctorService doctorService;
    /*Injecting the Doctor Service by Autowire annotation*/
    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /*This GET mapping method is responsible for saving doctores into redis database*/
    @GetMapping("doctor/{emailId}")
    public void saveDoctorRedis(@PathVariable  String emailId){
        try{
            doctorService.saveDoctorRedis(emailId);
        }
        catch(Exception exception)
        {
            logger.error("Error saving Doctor into redis");
        }
    }
    /*This POST mapping method is responsible for saving Doctors into MongoDB*/
    @PostMapping("doctorm")
    public ResponseEntity<Doctor> saveDoctorMongoDB(@RequestBody  Doctor doctor){
        try{
            Doctor doctor1 = doctorService.saveDoctorMongoDB(doctor);
            return new ResponseEntity<Doctor>(doctor1, HttpStatus.CREATED);
        }
        catch(Exception exception)
        {
            logger.error("Error saving Doctor into the MongoDB");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /*This Get method is used for finding the doctor via Email-Id*/
    @GetMapping("doctorm/{emailId}")
    public ResponseEntity<Doctor> getDoctorByEmailId(@PathVariable  String emailId) throws DoctorNotFoundException {
        try{
            Doctor doctor = doctorService.getDoctorByEmailId(emailId);
            return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
        }
        catch(Exception exception)
        {
            logger.error("Can't find Doctor with this email-id");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /*This Get method is used for displaying all doctors in the MongoDB*/
    @GetMapping("doctorm")
    public ResponseEntity<List<Doctor>> getAllDoctors() throws DatabaseEmptyException {
        try{
            List<Doctor> list =  doctorService.getAllDoctors();
            return new ResponseEntity<List<Doctor>>(list, HttpStatus.OK);
        }
        catch(Exception exception)
        {
            logger.error("Error getting doctors in the database");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /*This delete mapping method is used for deleting the doctor from redis*/
    @DeleteMapping("doctor/{emailId}")
    public ResponseEntity<String> deleteDoctorRedis(@PathVariable String emailId) throws DoctorNotFoundException {
        try{
            doctorService.deleteDoctorRedis(emailId) ;
            return new ResponseEntity<String>("Deleted", HttpStatus.OK);
        }
        catch(Exception exception)
        {
            logger.error("Error deleting doctors in the database");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
    /*This get mapping method is used for displaying all the doctor from redis*/

    @GetMapping("doctor")
    public ResponseEntity<List<Doctor>> getAllDoctorRedis() throws DatabaseEmptyException {
        try{
            List<Doctor> list = doctorService.getAllDoctorRedis();
            return new ResponseEntity<List<Doctor>>(list, HttpStatus.OK);
        }
        catch(Exception exception)
        {
            logger.error("Error displaying doctors in the database");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
