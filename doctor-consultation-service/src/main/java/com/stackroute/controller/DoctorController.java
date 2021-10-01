package com.stackroute.controller;

import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;
import com.stackroute.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class DoctorController {
    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> setStatus(@RequestBody int id) throws DoctorNotFoundException {
        String result = doctorService.changeStatus(id);
        return new ResponseEntity<String>(result, HttpStatus.OK );
    }
    @GetMapping("/{status}")
    public ResponseEntity<List<Doctor>> findByStatus(@RequestBody int status) throws DoctorNotFoundException {
        List<Doctor> result = doctorService.findByStatus(status);
        return new ResponseEntity<List<Doctor>>(result, HttpStatus.OK );
    }

    @PostMapping("/doctor")
    public ResponseEntity<Doctor> saveUser(@RequestBody Doctor doctor)
    {
        Doctor savedDoctor = doctorService.saveDoctor(doctor);
        return new ResponseEntity<Doctor>(savedDoctor, HttpStatus.CREATED );
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllUser()
    {
        return new ResponseEntity<List<Doctor>>(doctorService.getAll(), HttpStatus.OK);
    }


}
