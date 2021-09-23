package com.stackroute.repository;


import com.stackroute.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepo extends JpaRepository<Patient, String> {
    Patient save(Patient patient);
}
