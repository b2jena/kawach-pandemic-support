package com.stackroute.resource.controller;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.MedicalSosRequest;
import com.stackroute.resource.model.Resources;
import com.stackroute.resource.service.BedService;
import com.stackroute.resource.service.EquipmentService;
import com.stackroute.resource.service.MedicalSosRequestService;
import com.stackroute.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stackroute.resource.model.Equipments;
import com.stackroute.resource.model.Beds;



import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(value="*")
public class MedicalSosRequestController {
    private MedicalSosRequestService medicalSosRequestService;
    private ResourceService resourceService;
    private BedService bedService;
    private EquipmentService equipmentService;

    @Autowired
    public MedicalSosRequestController(MedicalSosRequestService medicalSosRequestService, ResourceService resourceService, EquipmentService equipmentService, BedService bedService){
        this.medicalSosRequestService = medicalSosRequestService;
        this.resourceService = resourceService;
        this.equipmentService=equipmentService;
        this.bedService=bedService;
    }



    @PostMapping("sos/createSos")
    public ResponseEntity<MedicalSosRequest> saveSosRequest  (@RequestBody MedicalSosRequest medicalSosRequest) throws NullValueException {
        MedicalSosRequest savedSosRequest = medicalSosRequestService.saveSosRequest(medicalSosRequest);
        return new ResponseEntity<>(savedSosRequest,HttpStatus.CREATED);
    }

    @GetMapping("sos/getMedSOS")
    public ResponseEntity<List<MedicalSosRequest>> getMedicineSOS(){
        return new ResponseEntity<List<MedicalSosRequest>>((List<MedicalSosRequest>)medicalSosRequestService.getMedSOS(),HttpStatus.OK);
    }

    @GetMapping("sos/getBedSOS")
    public ResponseEntity<List<MedicalSosRequest>> getBedSOS(){
        return new ResponseEntity<List<MedicalSosRequest>>((List<MedicalSosRequest>)medicalSosRequestService.getBedSOS(),HttpStatus.OK);
    }

    @GetMapping("sos/getEquipSOS")
    public ResponseEntity<List<MedicalSosRequest>> EquipSOS(){
        return new ResponseEntity<List<MedicalSosRequest>>((List<MedicalSosRequest>)medicalSosRequestService.getEquipSOS(),HttpStatus.OK);
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
    public ResponseEntity<List<Equipments>> getAllEquipment(@PathVariable("city") String city){
        return new ResponseEntity<List<Equipments>>((List<Equipments>)equipmentService.getEquipmentByCity(city),HttpStatus.OK);
    }

    @GetMapping("sos/getBeds/{city}")
    public ResponseEntity<List<Beds>> getAllBeds(@PathVariable("city") String city){
        return new ResponseEntity<List<Beds>>((List<Beds>)bedService.getAllBedsByCity(city),HttpStatus.OK);
    }

    @GetMapping("sos/printMessageMedicines/{city}")
    public ResponseEntity<String> getMessageMedicines(@PathVariable("city") String city){
        List<Resources> result=resourceService.getAllMedicine(city);
        String message="Medicine"+result.get(0).getMedicineName() +" is available in myCity"+result.get(0).getPharmacy() + " address: "+result.get(0).getAddress()+ " Kindly connect Mr/Mrs : "+result.get(0).getContactPerson()+" (Phone number: "+ result.get(0).getMobileNumber()+ "\n Get Well Soon, Stay safe.";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("sos/printMessageEquipments/{cityE}")
    public ResponseEntity<String> getMessageEquipments(@PathVariable("cityE") String cityE){
        List<Equipments> result=equipmentService.getEquipmentByCity(cityE);
        String message="Equipment"+result.get(0).getEquipmentName() +" is available in myCity"+result.get(0).getVerificationStatus() + " address: "+result.get(0).getAddress()+ " Kindly connect Mr/Mrs : "+result.get(0).getContactPerson()+" (Phone number: "+ result.get(0).getMobileNumber()+ "\n Get Well Soon, Stay safe.";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("sos/printMessageBeds/{cityB}")
    public ResponseEntity<String> getMessageBeds(@PathVariable("cityB") String cityB){
        List<Beds> result=bedService.getAllBedsByCity(cityB);
        String message="Beds"+result.get(0).getBedType() + " is available in myCity"+result.get(0).getVerificationStatus() + " address: "+result.get(0).getAddress()+ " Kindly connect Mr/Mrs : "+result.get(0).getContactPerson()+" (Phone number: "+ result.get(0).getMobileNumber()+ "\n Get Well Soon, Stay safe.";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("sos/getSOSMed")
    public ResponseEntity<MedicalSosRequest> getSOSMed()
    {
        return new ResponseEntity<>(medicalSosRequestService.getSOSMed(), HttpStatus.OK);
    }

    @GetMapping("sos/getSOSEquip")
    public ResponseEntity<MedicalSosRequest> getSOSEquip()
    {
        return new ResponseEntity<>(medicalSosRequestService.getSOSEquip(), HttpStatus.OK);
    }

    @GetMapping("sos/getSOSBed")
    public ResponseEntity<MedicalSosRequest> getSOSBed()
    {
        return new ResponseEntity<>(medicalSosRequestService.getSOSBed(), HttpStatus.OK);
    }

    @PutMapping("sos/updateStatus")
    public void closeSOS(@RequestBody MedicalSosRequest sos) {
        medicalSosRequestService.closeSOS(sos.getRequestId());
    }
//
//    {
//        List<Resources> result = resourceService.getSpecificResource(requirment, myCity);
//        String message = "Medicine" + result.get(0).getMedicineName() + " is avilable in myCity" + result.get(0).getAvalabilityPlace() + " address: " + result.get(0).getAddress() + " Kindly connect Mr/Mrs : " + result.get(0).getContactPersonName() + " (Phone number: " + result.get(0).getContactMobileNumber() + "\n Get Well Soon, Stay safe.";
//        return ResponseEntity.status(HttpStatreturn ResponseEntity.status(HttpStatus.OK).body(message);
//    }
}
