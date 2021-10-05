package com.stackroute.service;

import com.stackroute.exception.DoctorAlreadyPresentException;
import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;

import java.util.List;
import java.util.Set;

public interface DoctorService {
    void saveDoctorRedis(Doctor doctor) throws DoctorAlreadyPresentException;

    Set<Doctor> findAllDoctorRedis() throws DoctorNotFoundException;

    Doctor findByEmail(String id) ;

    List<Doctor> findByStatus(String status) throws DoctorNotFoundException;

    void updateStatusDoctorRedis(Doctor doctor) throws DoctorNotFoundException;
}
