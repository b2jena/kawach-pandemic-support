package com.stackroute.resource.controller;

import com.stackroute.resource.Service.ResourceService;
import com.stackroute.resource.Model.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResourceController {

    private ResourceService resourceService;

    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("medicine/create")
    public ResponseEntity<Resources> saveResource(@RequestBody Resources resources){
        Resources savedResources = resourceService.SaveResource(resources);
        return new ResponseEntity<Resources>(savedResources,HttpStatus.CREATED);
    }

    @GetMapping("medicine/getAll")
    public ResponseEntity<List<Resources>> getAllResources(){
        return new ResponseEntity<List<Resources>>((List<Resources>)resourceService.getAllResources(),HttpStatus.OK);
    }

    @PutMapping("medicine/update")
    public ResponseEntity<Resources> updateResource(@RequestBody Resources resources){
        Resources updatedResources = resourceService.UpdateResource(resources);
        return new ResponseEntity<Resources>
                (updatedResources,HttpStatus.CREATED);
    }
}
