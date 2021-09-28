package com.stackroute.resource.repository;

import com.stackroute.resource.model.MedicalSosRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicalSosRequestRepository extends MongoRepository<MedicalSosRequest, UUID> {
}
