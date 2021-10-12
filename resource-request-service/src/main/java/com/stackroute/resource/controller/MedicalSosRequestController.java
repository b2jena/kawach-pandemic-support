package com.stackroute.resource.controller;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.MedicalSosRequest;
import com.stackroute.resource.model.Resources;
import com.stackroute.resource.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private RabbitMqSender rabbitMqSender;

    private static final Logger logger = LoggerFactory.getLogger(MedicalSosRequestController.class);

    @Autowired
    public MedicalSosRequestController(MedicalSosRequestService medicalSosRequestService, ResourceService resourceService, EquipmentService equipmentService, BedService bedService, RabbitMqSender rabbitMqSender){
        this.medicalSosRequestService = medicalSosRequestService;
        this.resourceService = resourceService;
        this.equipmentService=equipmentService;
        this.bedService=bedService;
        this.rabbitMqSender=rabbitMqSender;
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

    @GetMapping("sos/getMed/{city}/{requirement}")
    public ResponseEntity<List<Resources>> getAllMedicine(@PathVariable("city") String city, @PathVariable("requirement") String requirement){
        List<Resources> result=resourceService.getAllMedicine(city, requirement);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("sos/getEquipment/{city}/{requirement}")
    public ResponseEntity<List<Equipments>> getAllEquipment(@PathVariable("city") String city, @PathVariable("requirement") String requirement){
        List<Equipments> result=equipmentService.getEquipmentByCity(city, requirement);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("sos/getBeds/{city}/{requirement}")
    public ResponseEntity<List<Beds>> getAllBeds(@PathVariable("city") String city, @PathVariable("requirement") String requirement){
        List<Beds> result=bedService.getAllBedsByCity(city, requirement);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("sos/printMessageMedicines/{city}/{requirement}")
    public ResponseEntity<String> getMessageMedicines(@PathVariable("city") String city, @PathVariable("requirement") String requirement){
        List<Resources> result=resourceService.getAllMedicine(city, requirement);
        String message="Medicine"+result.get(0).getMedicineName() +" is available in myCity"+result.get(0).getPharmacy() + " address: "+result.get(0).getAddress()+ " Kindly connect Mr/Mrs : "+result.get(0).getContactPerson()+" (Phone number: "+ result.get(0).getMobileNumber()+ "\n Get Well Soon, Stay safe.";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("sos/printMessageEquipments/{cityE}/{requirement}")
    public ResponseEntity<String> getMessageEquipments(@PathVariable("cityE") String cityE, @PathVariable("requirement") String requirement){
        List<Equipments> result=equipmentService.getEquipmentByCity(cityE, requirement);
        String message="Equipment"+result.get(0).getEquipmentName() +" is available in myCity"+result.get(0).getVerificationStatus() + " address: "+result.get(0).getAddress()+ " Kindly connect Mr/Mrs : "+result.get(0).getContactPerson()+" (Phone number: "+ result.get(0).getMobileNumber()+ "\n Get Well Soon, Stay safe.";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("sos/printMessageBeds/{cityB}/{requirement}")
    public ResponseEntity<String> getMessageBeds(@PathVariable("cityB") String cityB, @PathVariable("requirement") String requirement){
        List<Beds> result=bedService.getAllBedsByCity(cityB, requirement);
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
        logger.info("Message from frontend when closing:"+sos.getFormStatus());
        String message = sos.getFormStatus()+"&&"+sos.getEmail();
        rabbitMqSender.send(message);
        medicalSosRequestService.closeSOS(sos.getRequestId());
    }
}
