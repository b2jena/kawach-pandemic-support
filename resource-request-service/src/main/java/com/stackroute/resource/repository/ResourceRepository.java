package com.stackroute.resource.repository;
import com.stackroute.resource.model.Resources;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
/*This is a repository class which manages the medicines using mongo repository*/
@Repository
public interface ResourceRepository extends MongoRepository<Resources, UUID>{
}
