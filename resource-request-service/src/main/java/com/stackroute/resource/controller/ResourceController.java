package com.stackroute.resource.controller;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Resources;
import com.stackroute.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class ResourceController {

    private ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("medicine/create")
    public ResponseEntity<Resources> saveResource(@RequestBody Resources resources) throws NullValueException {
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
