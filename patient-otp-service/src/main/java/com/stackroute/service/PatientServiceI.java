package com.stackroute.service;

import com.stackroute.entity.Patient;

import java.util.List;

public interface PatientServiceI {
    Patient saveUser(Patient patient);

    List<Patient> getAll();
}
