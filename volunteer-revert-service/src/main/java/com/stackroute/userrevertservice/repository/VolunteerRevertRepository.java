package com.stackroute.userrevertservice.repository;

import com.stackroute.userrevertservice.model.Volunteer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*Volunteer revert repository extending MongoRepository to store the data in the database*/
@Repository
public interface VolunteerRevertRepository extends MongoRepository<Volunteer, String> {
}
