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
import java.util.logging.Logger;

/* This is a controller class containing Api of saving and fetching the 
 * beds from mongoDB database.
 * This class is annotated with @RestController, @CrossOrigin and @RequestMapping annotation
 * 
 */
@RestController
@CrossOrigin(value="*")
@RequestMapping("api/v1/resource/")
public class BedController {
    private BedService bedService;
    private RabbitMqSender rabbitMqSender;

    /* Bed Service, 
     * Rabbit Mq Sender is injected in this controller class by @Autowired annotation
     */

    @Autowired
    public BedController(BedService bedService, RabbitMqSender rabbitMqSender) {
        this.bedService = bedService;
        this.rabbitMqSender = rabbitMqSender;
    }
    /*This Post Mapping method is responsible for saving bed in the mongoDB repository*/
    @PostMapping("bed/create/{addBy}")
    public ResponseEntity<Beds> saveBed(@RequestBody Beds beds,@PathVariable ("addBy") String addBy) throws NullValueException {
        Logger logger = Logger.getLogger(BedController.class.getName());
        try{
            rabbitMqSender.sendVolunteer(addBy, "Create_Bed_Resource");
            Beds savedBeds = bedService.saveBed(beds);

            return new ResponseEntity<>(savedBeds, HttpStatus.CREATED);
        } catch (Exception e){
            logger.info(e + " encountered");
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
