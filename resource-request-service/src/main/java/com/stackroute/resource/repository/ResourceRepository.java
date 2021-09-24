package com.stackroute.resource.repository;
import com.stackroute.resource.model.Resources;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends MongoRepository<Resources, Integer>{
}
