package com.stackroute.controller;

import com.stackroute.exception.DoctorAlreadyPresentException;
import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;
import com.stackroute.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value="*")
@RequestMapping("/api/v1/")
public class DoctorController {
    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("doctor/{emailId}")
    public void saveDoctorRedis(@PathVariable  String emailId) throws DoctorNotFoundException, DoctorAlreadyPresentException, DoctorNotFoundException, DoctorAlreadyPresentException {
        doctorService.saveDoctorRedis(emailId);
    }

    @PostMapping("doctorm")
    public ResponseEntity<Doctor> saveDoctorMongoDB(@RequestBody  Doctor doctor) throws DoctorNotFoundException, DoctorAlreadyPresentException {
        Doctor doctor1 = doctorService.saveDoctorMongoDB(doctor);
        return new ResponseEntity<Doctor>(doctor1, HttpStatus.OK);
    }

    @GetMapping("doctorm/{emailId}")
    public ResponseEntity<Doctor> getDoctorByEmailId(@PathVariable  String emailId) throws DoctorNotFoundException {
        Doctor doctor = doctorService.getDoctorByEmailId(emailId);
        return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
    }

    @GetMapping("doctorm")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
    @DeleteMapping("doctor/{emailId}")
    public String deleteDoctorRedis(@PathVariable String emailId) throws DoctorNotFoundException {
        doctorService.deleteDoctorRedis(emailId);
        return "Deleted";
    }

    @GetMapping("doctor")
    public List<Doctor> getAllDoctorRedis(){
        List<Doctor> list = doctorService.getAllDoctorRedis();
        return list;
    }
}
