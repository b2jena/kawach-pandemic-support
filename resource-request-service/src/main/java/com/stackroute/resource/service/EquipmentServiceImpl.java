package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Equipments;
import com.stackroute.resource.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    @Autowired
    MongoTemplate mongoTemplate;

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

    @Override
    public Equipments getUnverifiedEquipments() {
//        List<Equipments> equipments = equipmentRepository.findAll();
//        List<Equipments> unverified = equipments.stream().filter(c -> c.getVerificationStatus() == false)
//                .collect(Collectors.toList());
        Query query = new Query();
        query.addCriteria(Criteria.where("verificationStatus").is(false));
        List<Equipments> unverified = mongoTemplate.find(query, Equipments.class);

        return unverified == null ? null : unverified.get(0);
    }

    @Override
    public List<Equipments> getEquipmentByCity(String City) {
        Query query = new Query();
        query.addCriteria(Criteria.where("city").in(City));
        List<Equipments> request = mongoTemplate.find(query, Equipments.class);
        return request;
    }

}
