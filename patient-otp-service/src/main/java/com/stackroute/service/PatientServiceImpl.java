package com.stackroute.service;

import com.stackroute.entity.Patient;
import com.stackroute.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientServiceI{

    private PatientRepo patientRepo;
    @Autowired
    public PatientServiceImpl(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public Patient saveUser(Patient patient) {
        return patientRepo.save(patient);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepo.getAll();
    }
}
