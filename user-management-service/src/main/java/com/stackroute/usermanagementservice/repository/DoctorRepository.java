package com.stackroute.usermanagementservice.repository;

import com.stackroute.usermanagementservice.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/*Doctor repository extending the MongoRepository to store data in the database*/

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {
}
