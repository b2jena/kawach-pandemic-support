package com.stackroute.service;

import com.stackroute.model.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor setStatus(Doctor doctor, int status);
    List<Doctor> findByStatus();
}
