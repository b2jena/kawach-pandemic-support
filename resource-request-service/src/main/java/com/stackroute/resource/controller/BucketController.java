package com.stackroute.resource.controller;

import com.stackroute.resource.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/*This is a controller class containing Api of  uploading file in Amazon S3 bucket
 * This class is annotated with @RestController, @CrossOrigin and @RequestMapping annotation*/

@RestController
@CrossOrigin(value="*")
@RequestMapping("api/v1/resource/")
public class BucketController {

    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    Logger logger = LoggerFactory.getLogger(BucketController.class.getName());


    private StorageService service;

    /*Storage Service is injected in this controller class by @Autowired annotation*/
    @Autowired
    public BucketController(StorageService service) {
        this.service = service;
    }

    /*This Post Mapping method is responsible for uploading in Amazon S3 Bucket*/
    @PostMapping("/sos/createSos/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file){
        try {
            return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);
        }catch (Exception e){
            logger.error("Failed to save SOS Request");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
