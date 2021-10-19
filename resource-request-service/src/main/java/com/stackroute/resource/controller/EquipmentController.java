package com.stackroute.resource.controller;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Equipments;
import com.stackroute.resource.service.EquipmentService;
import com.stackroute.resource.service.RabbitMqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/* This is a controller class containing Api of saving and fetching the 
 * equipments from mongoDB database.
 * This class is annotated with @RestController, @CrossOrigin and @RequestMapping annotation
 * 
 */
@RestController
@CrossOrigin(value="*")
@RequestMapping("api/v1/resource/")
public class EquipmentController {
    private EquipmentService equipmentService;
    private RabbitMqSender rabbitMqSender;

    /* equipment Service, 
     * Rabbit Mq Sender is injected in this controller class by @Autowired annotation
     */
    @Autowired
    public EquipmentController(EquipmentService equipmentService, RabbitMqSender rabbitMqSender) {
        this.equipmentService = equipmentService;
        this.rabbitMqSender = rabbitMqSender;
    }
    /*This Post Mapping method is responsible for saving equipments in the mongoDB repository*/
    @PostMapping("equipment/create/{addBy}")
    public ResponseEntity<Equipments> saveEquipment(@RequestBody Equipments equipments,@PathVariable ("addBy") String addBy) throws NullValueException {
        rabbitMqSender.sendVolunteer(addBy, "Create_Equipment_Resource");

        Equipments savedEquipments = equipmentService.saveEquipment(equipments);
        return new ResponseEntity<>(savedEquipments, HttpStatus.CREATED);
    }

    @GetMapping("equipment/getAll")
    public ResponseEntity<List<Equipments>> getAllEquipments(){
        return new ResponseEntity<List<Equipments>>((List<Equipments>)equipmentService.getAllEquipments(),HttpStatus.OK);
    }

    @GetMapping("equipment/getUnverified")
    public ResponseEntity<Equipments> getUnverifiedBed()
    {
        return new ResponseEntity<>(equipmentService.getUnverifiedEquipments(), HttpStatus.OK);
    }

    @PutMapping("equipment/update")
    public void VerifyEquipment(@RequestBody Equipments equipment)
    {
        equipmentService.UpdateEquipment(equipment.getEquipmentId());
    }
}
