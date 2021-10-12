package com.stackroute.resource.repository;

import com.stackroute.resource.model.Beds;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BedRepository extends MongoRepository<Beds, UUID> {

}
