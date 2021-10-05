package com.stackroute.repo;

import com.stackroute.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {
    Doctor findByEmailId(String emailId);
}
