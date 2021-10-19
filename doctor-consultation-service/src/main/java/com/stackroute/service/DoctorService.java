package com.stackroute.service;

import com.stackroute.exception.DatabaseEmptyException;
import com.stackroute.exception.DoctorAlreadyPresentException;
import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;

import java.util.List;

/*This is the Doctor Service class where abstract methods are declared*/
public interface DoctorService {
    /*This method will save Doctor into the Mongo repository*/
    Doctor saveDoctorMongoDB(Doctor doctor) throws DoctorNotFoundException;
    /*This method will get Doctor via the EmailId from the Mongo repository*/
    Doctor getDoctorByEmailId(String emailId) throws DoctorNotFoundException;
    /*This method will get all the Doctor from the Mongo repository*/
    List<Doctor> getAllDoctors() throws DatabaseEmptyException;
    /*This method will save Doctor into the Redis Database*/
    void saveDoctorRedis(String emailId) throws DoctorNotFoundException, DoctorAlreadyPresentException;
    /*This method will delete Doctor from the Redis Database*/
    void deleteDoctorRedis(String emailId) throws DoctorNotFoundException;
    /*This method will get all Doctor from the Redis Database*/
    List<Doctor> getAllDoctorRedis() throws DatabaseEmptyException;
}
