package com.stackroute.resource.service;

import com.stackroute.resource.model.MedicalSosRequest;
import com.stackroute.resource.repository.MedicalSosRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/*This is the implementation class of Medical SOS Request Service where abstract methods of  Medical SOS Request Service  are implemented*/
@Service
public class MedicalSosRequestServiceImpl implements MedicalSosRequestService {

    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    Logger logger = LoggerFactory.getLogger(MedicalSosRequestServiceImpl.class.getName());

    MedicalSosRequestRepository medicalSosRequestRepository;
    SequenceGeneratorService sequenceGeneratorService;

    /*Mongo Template is injected in this Medical SOS Request Service Implementation class by @Autowired annotation*/
    @Autowired
    MongoTemplate mongoTemplate;

    /*Medical Sos Request Repository is injected in this Medical SOS Request Service Implementation class by @Autowired annotation*/
    @Autowired
    public MedicalSosRequestServiceImpl(MedicalSosRequestRepository medicalSosRequestRepository) {
        this.medicalSosRequestRepository = medicalSosRequestRepository;
    }

    /*This Method is responsible for saving the SOS Requests in the mongoDB database */
    @Override
    public MedicalSosRequest saveSosRequest(MedicalSosRequest medicalSosRequest) throws Exception{
        try {
            medicalSosRequest.setRequestId(UUID.randomUUID());
            return medicalSosRequestRepository.save(medicalSosRequest);
        }catch (Exception e){
            logger.error(String.valueOf(e));
            throw  new Exception();
        }
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
    public void updateSosRequest(UUID requestId) {
        Query query = new Query(Criteria.where("_id").is(requestId));
        Update updateQuery = new Update();
        updateQuery.set("formStatus", "CLOSE");
        mongoTemplate.upsert(query, updateQuery, "SOSRequest");
    }
    /**
     * This method is used to fetch single sos whose type is medicine and form status in open.
     * Only the sos whose status is open is fetched. These methods are fetched randomly.
     */
    @Override
    public MedicalSosRequest getSOSMed() {
        Query query = new Query();
        query.addCriteria(Criteria.where("requestStatus").is("Medicine").and("formStatus").is("OPEN"));
        List<MedicalSosRequest> sos = mongoTemplate.find(query, MedicalSosRequest.class);

        if (sos.size() == 0) return null;

        int randomInd = ThreadLocalRandom.current().nextInt(0, sos.size());
        return sos.get(randomInd);
    }

    /**
     * This method is used to fetch single sos whose type is equipment and form status in open.
     * Only the sos whose status is open is fetched. These methods are fetched randomly.
     */
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
        updateQuery.set("formStatus", "CLOSE");
        mongoTemplate.upsert(query, updateQuery, MedicalSosRequest.class);
    }
}
