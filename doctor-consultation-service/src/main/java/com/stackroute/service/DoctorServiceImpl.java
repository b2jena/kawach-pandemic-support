package com.stackroute.service;

import com.stackroute.exception.DoctorAlreadyPresentException;
import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;
import com.stackroute.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    //defining the Hash key for the redis db.
    public static final String HASH_KEY = "Doctor";
    private DoctorRepository doctorRepository;
    //Autowiring the redis template to use the redis database.
    private RedisTemplate redisTemplate;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, RedisTemplate redisTemplate) {
        this.doctorRepository = doctorRepository;
        this.redisTemplate = redisTemplate;
    }


    //method to save he doctor information into the mongodb database.
    @Override
    public Doctor saveDoctorMongoDB(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    //To get the doctor information from mongodb
    @Override
    public Doctor getDoctorByEmailId(String emailId) throws DoctorNotFoundException {
        Doctor doctor = doctorRepository.findByEmailId(emailId);
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor not found");
        } else {
            return doctor;
        }
    }

    //To get all doctors present in the mongoDB databse
    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    //To save the doctor form mongodb to redis.
    @Override
    public void saveDoctorRedis(String emailId) throws DoctorNotFoundException, DoctorAlreadyPresentException {
        Doctor doctor = doctorRepository.findByEmailId(emailId);
        redisTemplate.opsForHash().put(HASH_KEY, doctor.getEmailId(), doctor);

    }

    //To delete the doctor databse from redis.
    @Override
    public void deleteDoctorRedis(String emailId) throws DoctorNotFoundException {
        if (!redisTemplate.opsForHash().hasKey(HASH_KEY, emailId)) {
            throw new DoctorNotFoundException("Doctor not found");
        } else {
            redisTemplate.opsForHash().delete(HASH_KEY, emailId);
        }
    }

    //To get all the redis doctors.
    @Override
    public List<Doctor> getAllDoctorRedis() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }
}
