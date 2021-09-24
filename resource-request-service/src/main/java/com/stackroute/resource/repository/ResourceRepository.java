package com.stackroute.resource.Repository;
import com.stackroute.resource.Model.Resources;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends MongoRepository<Resources, Integer>{
}
