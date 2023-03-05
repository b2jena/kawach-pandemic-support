package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Equipments;
import com.stackroute.resource.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    MongoTemplate mongoTemplate;
    private EquipmentRepository equipmentRepository;

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

    @Override
    public Equipments getUnverifiedEquipments() {
        Query query = new Query();
        query.addCriteria(Criteria.where("verificationStatus").is(false));
        List<Equipments> unverified = mongoTemplate.find(query, Equipments.class);

        if (unverified.size() == 0) return null;

        int randomInd = ThreadLocalRandom.current().nextInt(0, unverified.size());
        return unverified.get(randomInd);
    }

    @Override
    public void UpdateEquipment(UUID equipId) {
        System.out.println("medId = " + equipId);
        Query query = new Query(Criteria.where("equipmentId").is(equipId));
        Update updateQuery = new Update();
        updateQuery.set("verificationStatus", true);
        mongoTemplate.upsert(query, updateQuery, Equipments.class);
    }

    public List<Equipments> getEquipmentByCity(String City, String requirement) {
        Query query = new Query();
        query.addCriteria(Criteria.where("city").regex(City, "i").and("equipmentName").regex(requirement, "i"));
        List<Equipments> request = mongoTemplate.find(query, Equipments.class);
        return request;
    }

}
