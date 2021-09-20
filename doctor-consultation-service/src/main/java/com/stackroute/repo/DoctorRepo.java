package com.stackroute.repo;

import com.stackroute.model.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepo extends CrudRepository<Doctor, String> {
}
