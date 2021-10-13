package com.stackroute.resource.controller;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Beds;
import com.stackroute.resource.service.BedService;
import com.stackroute.resource.service.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value="*")
@RequestMapping("api/v1/resource/")
public class BedController {
    private BedService bedService;
    private RabbitMqSender rabbitMqSender;

    @Autowired
    public BedController(BedService bedService, RabbitMqSender rabbitMqSender) {
        this.bedService = bedService;
        this.rabbitMqSender = rabbitMqSender;
    }

    @PostMapping("bed/create/{addBy}")
    public ResponseEntity<Beds> saveBed(@RequestBody Beds beds,@PathVariable ("addBy") String addBy) throws NullValueException {
        //System.out.println("bikash"+beds.getBedType());

        try{
            rabbitMqSender.sendVolunteer(addBy, "Create_Bed_Resource");
            Beds savedBeds = bedService.saveBed(beds);

            return new ResponseEntity<>(savedBeds, HttpStatus.CREATED);
        } catch (Exception exc){
            System.out.println(exc);
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

    }

    @GetMapping("bed/getAll")
    public ResponseEntity<List<Beds>> getAllBeds(){
        return new ResponseEntity<List<Beds>>((List<Beds>)bedService.getAllBeds(),HttpStatus.OK);
    }

    @GetMapping("bed/getUnverified")
    public ResponseEntity<Beds> getUnverifiedBed()
    {
        return new ResponseEntity<>(bedService.getUnverifiedBed(), HttpStatus.OK);
    }

    @PutMapping("bed/update")
    public void putVerifiedBed(@RequestBody Beds beds) throws NullValueException {
        bedService.updateBed(beds.getBedId());
    }
}
