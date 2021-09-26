package com.stackroute.resource.repository;

import com.stackroute.resource.model.Beds;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface BedRepository extends MongoRepository<Beds, UUID> {
}
