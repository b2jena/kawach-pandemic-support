package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Beds;
import com.stackroute.resource.model.Resources;
import com.stackroute.resource.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResourceServiceImpl implements ResourceService {
    private ResourceRepository resourceRepository;
    SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Resources saveResource(Resources resources) throws NullValueException {
        if (resources.getMedicineName() == null || resources.getPharmacy() == null || resources.getAddress() == null || resources.getCity() == null || resources.getContactPerson() == null || resources.getMobileNumber() == null) {
            throw new NullValueException();
        } else if (resources.getMedicineName().equals("") || resources.getPharmacy().equals("") || resources.getAddress().equals("") || resources.getCity().equals("") || resources.getContactPerson().equals("") || resources.getMobileNumber().equals("")) {
            throw new NullValueException();
        } else {
            resources.setId(UUID.randomUUID());
            return resourceRepository.save(resources);
        }
    }

    @Override
    public List<Resources> getAllResources() {
        return (List<Resources>) resourceRepository.findAll();
    }

    @Override
    public Resources updateResource(Resources resources) {
        return resourceRepository.save(resources);
    }

    @Override
    public List<Resources> getAllMedicine(String City) {
        Query query = new Query();
        query.addCriteria(Criteria.where("city").in(City));
        List<Resources> request = mongoTemplate.find(query, Resources.class);
        return request;
    }

    @Override
    public List<Resources> getAllEquipment(String City) {
        Query query = new Query();
        query.addCriteria(Criteria.where("city").in(City));
        List<Resources> request = mongoTemplate.find(query, Resources.class);
        return request;
    }

    @Override
    public List<Resources> getAllBeds(String City) {
        Query query = new Query();
        query.addCriteria(Criteria.where("city").in(City));
        List<Resources> request = mongoTemplate.find(query, Resources.class);
        return request;
    }

    @Override
    public Resources getUnverifiedResources() {
        Query query = new Query();
        query.addCriteria(Criteria.where("verificationStatus").is(false));
        List<Resources> unverified = mongoTemplate.find(query, Resources.class);

        return unverified == null ? null : unverified.get(0);
    }

    @Override
    public void UpdateMedicine(UUID medId) {
        System.out.println("medId = " + medId);
        Query query = new Query(Criteria.where("id").is(medId));
        Update updateQuery = new Update();
        updateQuery.set("verificationStatus",true);
        mongoTemplate.upsert(query,updateQuery, Resources.class);
    }
}
