package com.stackroute.service;

import com.stackroute.exception.DoctorAlreadyPresentException;
import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor saveDoctorMongoDB(Doctor doctor);

    Doctor getDoctorByEmailId(String emailId) throws DoctorNotFoundException;

    List<Doctor> getAllDoctors();

    void saveDoctorRedis(String emailId) throws DoctorNotFoundException, DoctorAlreadyPresentException;

    void deleteDoctorRedis(String emailId) throws DoctorNotFoundException;

    List<Doctor> getAllDoctorRedis();
}
