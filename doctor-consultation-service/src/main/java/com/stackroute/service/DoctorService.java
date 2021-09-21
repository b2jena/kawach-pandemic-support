package com.stackroute.service;

import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;

import java.util.List;

public interface DoctorService {
    public String changeStatus(int id) throws DoctorNotFoundException;
    public Doctor findById(int id);
    public List<Doctor> findByStatus(int status) throws DoctorNotFoundException;
    Doctor saveDoctor(Doctor doctor);
    List<Doctor> getAll();
}
