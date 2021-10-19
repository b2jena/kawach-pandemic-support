package com.stackroute.resource.controller;

import com.stackroute.resource.model.Beds;
import com.stackroute.resource.model.Equipments;
import com.stackroute.resource.model.MedicalSosRequest;
import com.stackroute.resource.model.Resources;
import com.stackroute.resource.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


/*This is a controller class containing Api of saving, fetching, updating and deleting the SOS Requests  from mongoDB database
 * This class is annotated with @RestController, @CrossOrigin and @RequestMapping annotation*/


@RestController
@CrossOrigin(value="*")
@RequestMapping("api/v1/resource/")
public class MedicalSosRequestController {

    private MedicalSosRequestService medicalSosRequestService;
    private ResourceService resourceService;
    private BedService bedService;
    private EquipmentService equipmentService;
    private RabbitMqSender rabbitMqSender;

    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    private static final Logger logger = LoggerFactory.getLogger(MedicalSosRequestController.class);

    /*Medical Sos Request Service, Resource Service, Equipment Service, Bed Service, Rabbit Mq Sender is injected in this controller class by @Autowired annotation*/
    @Autowired
    public MedicalSosRequestController(MedicalSosRequestService medicalSosRequestService, ResourceService resourceService, EquipmentService equipmentService, BedService bedService, RabbitMqSender rabbitMqSender){
        this.medicalSosRequestService = medicalSosRequestService;
        this.resourceService = resourceService;
        this.equipmentService=equipmentService;
        this.bedService=bedService;
        this.rabbitMqSender=rabbitMqSender;
    }


    /*This Post Mapping method is responsible for saving the SOS Request in the mongoDB repository*/
    @PostMapping("sos/createSos")
    public ResponseEntity<MedicalSosRequest> saveSosRequest(@RequestBody MedicalSosRequest medicalSosRequest) {
        try {
            MedicalSosRequest savedSosRequest = medicalSosRequestService.saveSosRequest(medicalSosRequest);
            return new ResponseEntity<>(savedSosRequest, HttpStatus.CREATED);
        } catch (Exception exception) {
            logger.error("Failed to save SOS Request");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("sos/getMedSOS")
    public ResponseEntity<List<MedicalSosRequest>> getMedicineSOS() {
        return new ResponseEntity<List<MedicalSosRequest>>((List<MedicalSosRequest>) medicalSosRequestService.getMedSOS(), HttpStatus.OK);
    }

    @GetMapping("sos/getBedSOS")
    public ResponseEntity<List<MedicalSosRequest>> getBedSOS() {
        return new ResponseEntity<List<MedicalSosRequest>>((List<MedicalSosRequest>) medicalSosRequestService.getBedSOS(), HttpStatus.OK);
    }

    @GetMapping("sos/getEquipSOS")
    public ResponseEntity<List<MedicalSosRequest>> EquipSOS() {
        return new ResponseEntity<List<MedicalSosRequest>>((List<MedicalSosRequest>) medicalSosRequestService.getEquipSOS(), HttpStatus.OK);
    }

    @GetMapping("sos/getSos")
    public ResponseEntity<List<MedicalSosRequest>> getSosRequest() {
        return new ResponseEntity<List<MedicalSosRequest>>((List<MedicalSosRequest>) medicalSosRequestService.getSosRequest(), HttpStatus.OK);
    }

    @PutMapping(path = "sos/statusSos/{requestId}")
    public ResponseEntity<String> updateSosRequest(@PathVariable("requestId") UUID requestId) {
        medicalSosRequestService.updateSosRequest(requestId);
        return new ResponseEntity<String>("updated successfully", HttpStatus.OK);
    }

//    @GetMapping("sos/getRes/{city}")
//    public ResponseEntity<List<Resources>> getAllMedicine(@PathVariable("city") String city) {
//        return new ResponseEntity<List<Resources>>((List<Resources>) resourceService.getAllMedicine(city), HttpStatus.OK);
//    }

//    @GetMapping("sos/getEquipment/{city}")
//    public ResponseEntity<List<Equipments>> getAllEquipment(@PathVariable("city") String city) {
//        return new ResponseEntity<List<Equipments>>((List<Equipments>) equipmentService.getEquipmentByCity(city), HttpStatus.OK);
//    }

//    @GetMapping("sos/getBeds/{city}")
//    public ResponseEntity<List<Beds>> getAllBeds(@PathVariable("city") String city) {
//        return new ResponseEntity<List<Beds>>((List<Beds>) bedService.getAllBedsByCity(city), HttpStatus.OK);
//    }

//    @GetMapping("sos/printMessageMedicines/{city}")
//    public ResponseEntity<String> getMessageMedicines(@PathVariable("city") String city) {
//        List<Resources> result = resourceService.getAllMedicine(city);
//        String message = "Medicine" + result.get(0).getMedicineName() + " is available in myCity" + result.get(0).getPharmacy() + " address: " + result.get(0).getAddress() + " Kindly connect Mr/Mrs : " + result.get(0).getContactPerson() + " (Phone number: " + result.get(0).getMobileNumber() + "\n Get Well Soon, Stay safe.";
//        return ResponseEntity.status(HttpStatus.OK).body(message);
//    }

//    @GetMapping("sos/printMessageEquipments/{cityE}")
//    public ResponseEntity<String> getMessageEquipments(@PathVariable("cityE") String cityE) {
//        List<Equipments> result = equipmentService.getEquipmentByCity(cityE);
//        String message = "Equipment" + result.get(0).getEquipmentName() + " is available in myCity" + result.get(0).getVerificationStatus() + " address: " + result.get(0).getAddress() + " Kindly connect Mr/Mrs : " + result.get(0).getContactPerson() + " (Phone number: " + result.get(0).getMobileNumber() + "\n Get Well Soon, Stay safe.";
//        return ResponseEntity.status(HttpStatus.OK).body(message);
//    }

//    @GetMapping("sos/printMessageBeds/{cityB}")
//    public ResponseEntity<String> getMessageBeds(@PathVariable("cityB") String cityB) {
//        List<Beds> result = bedService.getAllBedsByCity(cityB);
//        String message = "Beds" + result.get(0).getBedType() + " is available in myCity" + result.get(0).getVerificationStatus() + " address: " + result.get(0).getAddress() + " Kindly connect Mr/Mrs : " + result.get(0).getContactPerson() + " (Phone number: " + result.get(0).getMobileNumber() + "\n Get Well Soon, Stay safe.";
//    }
    /**
     * This getAllMedicine controller method is used to fetch the available resources
     * from the backend taking city and requirement as path variables.
     * This is similar to that of getAllEquipment and getAllBeds
     */
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
    /**
     * These 3 controller method call corresponding service methods.
     */
    @GetMapping("sos/getSOSMed")
    public ResponseEntity<MedicalSosRequest> getSOSMed() {
        return new ResponseEntity<>(medicalSosRequestService.getSOSMed(), HttpStatus.OK);
    }

    @GetMapping("sos/getSOSEquip")
    public ResponseEntity<MedicalSosRequest> getSOSEquip() {
        return new ResponseEntity<>(medicalSosRequestService.getSOSEquip(), HttpStatus.OK);
    }

    @GetMapping("sos/getSOSBed")
    public ResponseEntity<MedicalSosRequest> getSOSBed() {
        return new ResponseEntity<>(medicalSosRequestService.getSOSBed(), HttpStatus.OK);
    }

    /**
     * This method is called when a volunteer tries to close a form status.
     */
    @PutMapping("sos/updateStatus")
    public void closeSOS(@RequestBody MedicalSosRequest sos) {
        logger.info("Message from frontend when closing:"+sos.getStrFormStatus());
        String message = sos.getStrFormStatus()+"&&"+sos.getStrEmail();
        rabbitMqSender.send(message);
        medicalSosRequestService.closeSOS(sos.getRequestId());
    }
}
