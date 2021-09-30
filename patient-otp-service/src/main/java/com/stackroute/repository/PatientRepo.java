package com.stackroute.repository;


import com.stackroute.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, String> {
    Patient save(Patient patient);
}
