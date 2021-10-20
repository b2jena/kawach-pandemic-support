package com.stackroute.resource.repository;

import com.stackroute.resource.model.Beds;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
/*This is a repository class which manages the beds using mongo repository*/
@Repository
public interface BedRepository extends MongoRepository<Beds, UUID> {

}
