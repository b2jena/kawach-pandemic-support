package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.MedicalSosRequest;
import com.stackroute.resource.repository.MedicalSosRequestRepository;
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
public class MedicalSosRequestServiceImpl implements MedicalSosRequestService {
    MedicalSosRequestRepository medicalSosRequestRepository;
    //ResourceRepository resourceRepository;
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    public MedicalSosRequestServiceImpl(MedicalSosRequestRepository medicalSosRequestRepository) {
        this.medicalSosRequestRepository = medicalSosRequestRepository;
    }

    @Override
    public MedicalSosRequest saveSosRequest(MedicalSosRequest medicalSosRequest) throws NullValueException {
        medicalSosRequest.setRequestId(UUID.randomUUID());
        return medicalSosRequestRepository.save(medicalSosRequest);
    }
    @Override
    public List<MedicalSosRequest> getSosRequest() {
        return (List<MedicalSosRequest>) medicalSosRequestRepository.findAll();
    }

    @Override
    public List<MedicalSosRequest> getMedSOS() {
        Query query = new Query(Criteria.where("requestStatus").is("Medicine").and("formStatus").is("OPEN"));
        List<MedicalSosRequest> request = mongoTemplate.find(query, MedicalSosRequest.class);
        return request;
    }

    @Override
    public List<MedicalSosRequest> getEquipSOS() {
        Query query = new Query(Criteria.where("requestStatus").is("Medical Equipment").and("formStatus").is("OPEN"));
        List<MedicalSosRequest> request = mongoTemplate.find(query, MedicalSosRequest.class);
        return request;
    }

    @Override
    public List<MedicalSosRequest> getBedSOS() {
        Query query = new Query(Criteria.where("requestStatus").is("Bed").and("formStatus").is("OPEN"));
        List<MedicalSosRequest> request = mongoTemplate.find(query, MedicalSosRequest.class);
        return request;
    }

    @Override
    public void updateSosRequest(UUID requestId){
        Query query = new Query(Criteria.where("_id").is(requestId));
        Update updateQuery = new Update();
        updateQuery.set("formStatus","CLOSE");
        mongoTemplate.upsert(query,updateQuery,"SOSRequest");
    }

    @Override
    public MedicalSosRequest getSOSMed() {
        Query query = new Query();
        query.addCriteria(Criteria.where("requestStatus").is("Medicine").and("formStatus").is("OPEN"));
        List<MedicalSosRequest> sos = mongoTemplate.find(query, MedicalSosRequest.class);

        if (sos.size() == 0) return null;

        int randomInd = ThreadLocalRandom.current().nextInt(0, sos.size());
        return sos.get(randomInd);
    }

    @Override
    public MedicalSosRequest getSOSEquip() {
        Query query = new Query();
        query.addCriteria(Criteria.where("requestStatus").is("Medical Equipment").and("formStatus").is("OPEN"));
        List<MedicalSosRequest> sos = mongoTemplate.find(query, MedicalSosRequest.class);

        if (sos.size() == 0) return null;

        int randomInd = ThreadLocalRandom.current().nextInt(0, sos.size());
        return sos.get(randomInd);
    }

    @Override
    public MedicalSosRequest getSOSBed() {
        Query query = new Query();
        query.addCriteria(Criteria.where("requestStatus").is("Bed").and("formStatus").is("OPEN"));
        List<MedicalSosRequest> sos = mongoTemplate.find(query, MedicalSosRequest.class);

        if (sos.size() == 0) return null;

        int randomInd = ThreadLocalRandom.current().nextInt(0, sos.size());
        return sos.get(randomInd);
    }

    @Override
    public void closeSOS(UUID requestId) {
        Query query = new Query(Criteria.where("_id").is(requestId));
        Update updateQuery = new Update();
        updateQuery.set("formStatus","CLOSE");
        mongoTemplate.upsert(query,updateQuery, MedicalSosRequest.class);
    }
}
