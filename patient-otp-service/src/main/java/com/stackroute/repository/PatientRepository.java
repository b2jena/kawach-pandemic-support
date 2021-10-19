package com.stackroute.repository;

import com.stackroute.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*This is a repository class which manages the Emails using JPA repository*/

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    /*This Method will save all the Emails of the patient into the H2 database */
    Patient save(Patient patient);
}
