package com.stackroute.userrevertservice.repository;

import com.stackroute.userrevertservice.model.Volunteer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRevertRepository extends MongoRepository<Volunteer, String> {
}
