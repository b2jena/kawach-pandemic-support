package com.stackroute.repo;

import com.stackroute.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*This is a repository class which manages the Doctor parameters using Mongo repository*/
@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {
    /*This method will find the doctor via EmailID.*/
    Doctor findByEmailId(String emailId);
}
