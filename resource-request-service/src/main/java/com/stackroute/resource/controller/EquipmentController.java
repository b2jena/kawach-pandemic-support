package com.stackroute.resource.controller;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Equipments;
import com.stackroute.resource.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(value="*")
@RequestMapping("api/v1/resource/")
public class EquipmentController {
    private EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("equipment/create")
    public ResponseEntity<Equipments> saveEquipment(@RequestBody Equipments equipments) throws NullValueException {
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
