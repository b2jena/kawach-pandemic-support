package com.stackroute.resource.controller;

import com.stackroute.resource.model.Beds;
import com.stackroute.resource.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class BedController {
    private BedService bedService;

    @Autowired
    public BedController(BedService bedService) {
        this.bedService = bedService;
    }

    @PostMapping("bed/create")
    public ResponseEntity<Beds> saveBed(@RequestBody Beds beds){
        Beds savedBeds = bedService.saveBed(beds);
        return new ResponseEntity<>(savedBeds, HttpStatus.CREATED);
    }

    @GetMapping("bed/getAll")
    public ResponseEntity<List<Beds>> getAllBeds(){
        return new ResponseEntity<List<Beds>>((List<Beds>)bedService.getAllBeds(),HttpStatus.OK);
    }
}
