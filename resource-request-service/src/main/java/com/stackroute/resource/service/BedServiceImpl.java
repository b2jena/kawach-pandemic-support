package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Beds;
import com.stackroute.resource.repository.BedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BedServiceImpl implements BedService{
    private BedRepository bedRepository;
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    public BedServiceImpl(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Beds saveBed(Beds beds) throws NullValueException {
        if (beds.getBedType() == null || beds.getAddress() == null || beds.getCity() == null || beds.getContactPerson() == null || beds.getMobileNumber() == null) {
            throw new NullValueException();
        } else if (beds.getBedType().equals("") || beds.getAddress().equals("") || beds.getCity().equals("") || beds.getContactPerson().equals("") || beds.getMobileNumber().equals("")) {
            throw new NullValueException();
        } else {
            beds.setBedId(UUID.randomUUID());
            return bedRepository.save(beds);
        }
    }
    @Override
    public List<Beds> getAllBeds() {
        return (List<Beds>) bedRepository.findAll();
    }

    @Override
    public Beds getUnverifiedBed()
    {
//        List<Beds> beds = bedRepository.findAll();
//        List<Beds> unverified = beds.stream().filter(c -> c.getVerificationStatus() == false)
//                .collect(Collectors.toList());

        Query query = new Query();
        query.addCriteria(Criteria.where("verificationStatus").is(false));
        List<Beds> unverified = mongoTemplate.find(query, Beds.class);

        return unverified == null ? null : unverified.get(0);
    }

    @Override
    public List<Beds> getAllBedsByCity(String City) {
        Query query = new Query();
        query.addCriteria(Criteria.where("city").in(City));
        List<Beds> request = mongoTemplate.find(query, Beds.class);
        return request;
    }


}
