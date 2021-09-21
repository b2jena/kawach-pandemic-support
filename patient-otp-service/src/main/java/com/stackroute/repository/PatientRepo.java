package com.stackroute.repository;

import com.stackroute.entity.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepo extends CrudRepository<Patient, String> {
}
