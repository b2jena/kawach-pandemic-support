package com.stackroute.resource.controller;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Resources;
import com.stackroute.resource.service.RabbitMqSender;
import com.stackroute.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/* This is a controller class containing Api of saving and fetching the 
 * medicines from mongoDB database.
 * This class is annotated with @RestController, @CrossOrigin and @RequestMapping annotation
 * 
 */
@RestController
@CrossOrigin(value="*")
@RequestMapping("api/v1/resource/")
public class ResourceController {

    private ResourceService resourceService;
    private RabbitMqSender rabbitMqSender;

    /* resource Service, 
     * Rabbit Mq Sender is injected in this controller class by @Autowired annotation
     */
    @Autowired
    public ResourceController(ResourceService resourceService, RabbitMqSender rabbitMqSender) {
        this.resourceService = resourceService;
        this.rabbitMqSender=rabbitMqSender;
    }
    /*This Post Mapping method is responsible for saving medicines in the mongoDB repository*/
    @PostMapping("medicine/create/{addBy}")
    public ResponseEntity<Resources> saveResource(@RequestBody Resources resources, @PathVariable ("addBy") String addBy) throws NullValueException {
        rabbitMqSender.sendVolunteer(addBy, "Create_Medicine_Resource");

        Resources savedResources = resourceService.saveResource(resources);
        return new ResponseEntity<>(savedResources,HttpStatus.CREATED);
    }

    @GetMapping("medicine/getAll")
    public ResponseEntity<List<Resources>> getAllResources(){
        return new ResponseEntity<List<Resources>>((List<Resources>)resourceService.getAllResources(),HttpStatus.OK);
    }

    @GetMapping("medicine/getUnverified")
    public ResponseEntity<Resources> getUnverifiedBed()
    {
        return new ResponseEntity<>(resourceService.getUnverifiedResources(), HttpStatus.OK);
    }

    @PutMapping("medicine/update")
    public void verifyMedicine(@RequestBody Resources med)
    {
        resourceService.UpdateMedicine(med.getId());
    }
}
