package com.stackroute.usermanagementservice.repository;

import com.stackroute.usermanagementservice.model.Volunteer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*Volunteer repository to store the data in the database*/

@Repository
public interface VolunteerRepository extends MongoRepository<Volunteer, String> {
}
