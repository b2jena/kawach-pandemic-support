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
//    @Autowired
//    public MedicalSosRequestServiceImpl(ResourceRepository resourceRepository) {
//        this.resourceRepository = resourceRepository;
//    }

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
    public void updateSosRequest(UUID requestId){
        Query query = new Query(Criteria.where("_id").is(requestId));
        Update updateQuery = new Update();
        updateQuery.set("requestStatus","Close");
        mongoTemplate.upsert(query,updateQuery,"SOSRequest");
    }

//    @Override
//    public List<Resources> getAllRes() {
//        return (List<Resources>) resourceRepository.findAll();
//    }


}
