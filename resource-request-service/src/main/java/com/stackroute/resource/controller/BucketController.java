package com.stackroute.resource.controller;

import com.stackroute.resource.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(value="*")
@RequestMapping("api/v1/resource/")
public class BucketController {


    private StorageService service;

    @Autowired
    public BucketController(StorageService service) {
        this.service = service;
    }

    @PostMapping("/sos/createSos/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(service.uploadFile(file), HttpStatus.OK);
    }

}
