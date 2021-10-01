package com.stackroute.resource.controller;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.MedicalSosRequest;
import com.stackroute.resource.model.Resources;
import com.stackroute.resource.service.MedicalSosRequestService;
import com.stackroute.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("http://localhost:4200")
public class MedicalSosRequestController {
    private MedicalSosRequestService medicalSosRequestService;
    private ResourceService resourceService;
    @Autowired
    public MedicalSosRequestController(MedicalSosRequestService medicalSosRequestService, ResourceService resourceService){
        this.medicalSosRequestService = medicalSosRequestService;
        this.resourceService = resourceService;
    }



    @PostMapping("sos/createSos")
    public ResponseEntity<MedicalSosRequest> saveSosRequest  (@RequestBody MedicalSosRequest medicalSosRequest) throws NullValueException {
        MedicalSosRequest savedSosRequest = medicalSosRequestService.saveSosRequest(medicalSosRequest);
        return new ResponseEntity<>(savedSosRequest,HttpStatus.CREATED);
    }

    @GetMapping("sos/getSos")
    public ResponseEntity<List<MedicalSosRequest>> getSosRequest(){
        return new ResponseEntity<List<MedicalSosRequest>>((List<MedicalSosRequest>)medicalSosRequestService.getSosRequest(),HttpStatus.OK);
    }

    @PutMapping(path="sos/statusSos/{requestId}")
    public ResponseEntity<String> updateSosRequest(@PathVariable("requestId") UUID requestId){
        medicalSosRequestService.updateSosRequest(requestId);
        return new ResponseEntity<String>("updated successfully" ,HttpStatus.OK );
    }

    @GetMapping("sos/getRes/{city}")
    public ResponseEntity<List<Resources>> getAllMedicine(@PathVariable("city") String city){
        return new ResponseEntity<List<Resources>>((List<Resources>)resourceService.getAllMedicine(city),HttpStatus.OK);
    }

    @GetMapping("sos/getEquipment/{city}")
    public ResponseEntity<List<Resources>> getAllEquipment(@PathVariable("city") String city){
        return new ResponseEntity<List<Resources>>((List<Resources>)resourceService.getAllEquipment(city),HttpStatus.OK);
    }

    @GetMapping("sos/getBeds/{city}")
    public ResponseEntity<List<Resources>> getAllBeds(@PathVariable("city") String city){
        return new ResponseEntity<List<Resources>>((List<Resources>)resourceService.getAllBeds(city),HttpStatus.OK);
    }

}
