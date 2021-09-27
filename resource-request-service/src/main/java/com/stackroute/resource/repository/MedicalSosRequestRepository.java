package com.stackroute.resource.repository;

import com.stackroute.resource.model.MedicalSosRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MedicalSosRequestRepository extends MongoRepository<MedicalSosRequest, UUID> {
}
