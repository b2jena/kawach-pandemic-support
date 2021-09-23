package com.stackroute.repository;


import com.stackroute.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, String> {
    Patient save(Patient patient);
    List<Patient> getAll();
}
