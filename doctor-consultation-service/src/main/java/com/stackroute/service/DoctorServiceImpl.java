package com.stackroute.service;

import com.stackroute.exception.DatabaseEmptyException;
import com.stackroute.exception.DoctorAlreadyPresentException;
import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;
import com.stackroute.repo.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/*This is the implementation class of Doctor Service where
abstract methods of Doctor Service are implemented*/
@Service
public class DoctorServiceImpl implements DoctorService{
    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class.getName());

    private DoctorRepository doctorRepository;
    /*Autowiring the redis template to use the redis database.*/
    private RedisTemplate redisTemplate;
    /*defining the Hash key for the redis db.*/
    public static final String HASH_KEY="Doctor";

    /*Doctor Repository and Redis Template is injected in this Doctor Service Implementation
        class by @Autowired annotation*/
    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, RedisTemplate redisTemplate) {
        this.doctorRepository = doctorRepository;
        this.redisTemplate = redisTemplate;
    }

    /*method to save he doctor information into the mongodb database.*/
    @Override
    public Doctor saveDoctorMongoDB(Doctor doctor) throws DoctorNotFoundException {
        try{
            return doctorRepository.save(doctor);
        }
        catch(Exception e)
        {
            logger.error("Error in registering with this email-id.");
            throw new DoctorNotFoundException();
        }
    }

    /*To get the doctor information from mongodb*/
    @Override
    public Doctor getDoctorByEmailId(String emailId) throws DoctorNotFoundException {
        try{Doctor doctor = doctorRepository.findByEmailId(emailId);
            return doctor;
        }
        catch(Exception e)
        {
            logger.error("Doctor not found");
            throw new DoctorNotFoundException();

        }
    }
    /*To get all doctors present in the mongoDB databse*/
    @Override
    public List<Doctor> getAllDoctors() throws DatabaseEmptyException {
        try{
            return doctorRepository.findAll();
        }
        catch(Exception e)
        {
            logger.error("No doctors found");
            throw new DatabaseEmptyException();
        }
    }

    /*To save the doctor form mongodb to redis.*/
    @Override
    public void saveDoctorRedis(String emailId) throws DoctorNotFoundException {
        try{
            Doctor doctor = doctorRepository.findByEmailId(emailId);
            redisTemplate.opsForHash().put(HASH_KEY, doctor.getStrEmailId(), doctor);
        }
        catch(Exception e)
        {
            logger.error("No such doctors.");
            throw new DoctorNotFoundException();
        }
}

    /*To delete the doctor databse from redis.*/
    @Override
    public void deleteDoctorRedis(String emailId) throws DoctorNotFoundException {
        try{
            redisTemplate.opsForHash().delete(HASH_KEY, emailId);
        }
        catch(Exception e){
            logger.error("Doctor not found");
            throw new DoctorNotFoundException();
        }
    }

    //To get all the redis doctors.
    @Override
    public List<Doctor> getAllDoctorRedis() throws DatabaseEmptyException {
        try{
            return redisTemplate.opsForHash().values(HASH_KEY);
        }
        catch (Exception e)
        {
            logger.error("No doctors found");
            throw new DatabaseEmptyException();
        }
    }
}
