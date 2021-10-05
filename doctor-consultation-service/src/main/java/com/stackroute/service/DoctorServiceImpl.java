package com.stackroute.service;

import com.stackroute.exception.DoctorAlreadyPresentException;
import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;
import com.stackroute.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;
    private RedisTemplate redisTemplate;
    public static final String HASH_KEY="Doctor";

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, RedisTemplate redisTemplate) {
        this.doctorRepository = doctorRepository;
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void saveDoctorRedis(Doctor doctor) throws DoctorAlreadyPresentException{
        redisTemplate.opsForSet().add(HASH_KEY, doctor.getEmail(),doctor);
    }

    @Override
    public Set<Doctor> findAllDoctorRedis() throws DoctorNotFoundException{
        return redisTemplate.opsForSet().members(HASH_KEY);
    }

    @Override
    public void updateStatusDoctorRedis(Doctor doctor) throws DoctorNotFoundException{
        redisTemplate.opsForSet().
    }

    @Override
    public Doctor findByEmail(String id) {
        return redisTemplate.opsForSet().f;
    }

    @Override
    public Doctor findById(String email) {
        System.out.println("called findById() from DB.");
        return (Doctor) redisTemplate.opsForHash().get(HASH_KEY,email);
    }
}
