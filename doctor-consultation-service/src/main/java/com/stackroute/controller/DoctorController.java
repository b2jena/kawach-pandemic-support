package com.stackroute.controller;

import com.stackroute.model.Doctor;
import com.stackroute.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/{staus}")
    public Doctor setStatus(@RequestBody Doctor doctor, int status){
        return doctorService.setStatus(doctor , status);
    }
    @GetMapping()
    public List<Doctor> findByStatus(){
        return doctorService.findByStatus();
    }

}
