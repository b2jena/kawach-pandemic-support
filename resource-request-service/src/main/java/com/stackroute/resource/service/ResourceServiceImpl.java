package com.stackroute.resource.service;
import com.stackroute.resource.model.Resources;
import com.stackroute.resource.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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
    public Resources saveResource(Resources resources) {
        resources.setId(UUID.randomUUID());
        return resourceRepository.save(resources);
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
}
