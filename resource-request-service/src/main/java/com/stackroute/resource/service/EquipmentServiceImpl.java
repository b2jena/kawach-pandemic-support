package com.stackroute.resource.service;

import com.stackroute.resource.model.Equipments;
import com.stackroute.resource.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class EquipmentServiceImpl implements EquipmentService{
    private EquipmentRepository equipmentRepository;
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }
    @Override
    public Equipments saveEquipment(Equipments equipments) {
        equipments.setId(UUID.randomUUID());
        return equipmentRepository.save(equipments);
    }
    @Override
    public List<Equipments> getAllEquipments() {
        return (List<Equipments>) equipmentRepository.findAll();
    }
}
