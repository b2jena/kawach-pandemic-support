package com.stackroute.resource.repository;

import com.stackroute.resource.model.Equipments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
/*This is a repository class which manages the equipments using mongo repository*/
@Repository
public interface EquipmentRepository extends MongoRepository<Equipments, UUID> {
}
