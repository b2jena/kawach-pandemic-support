package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
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
    public Equipments saveEquipment(Equipments equipments) throws NullValueException {
        if (equipments.getEquipmentName() == null || equipments.getHospital() == null || equipments.getAddress() == null || equipments.getCity() == null || equipments.getContactPerson() == null || equipments.getMobileNumber() == null) {
            throw new NullValueException();
        } else if (equipments.getEquipmentName().equals("") || equipments.getHospital().equals("") || equipments.getAddress().equals("") || equipments.getCity().equals("") || equipments.getContactPerson().equals("") || equipments.getMobileNumber().equals("")) {
            throw new NullValueException();
        } else {
            equipments.setEquipmentId(UUID.randomUUID());
            return equipmentRepository.save(equipments);
        }
    }
    @Override
    public List<Equipments> getAllEquipments() {
        return (List<Equipments>) equipmentRepository.findAll();
    }
}
