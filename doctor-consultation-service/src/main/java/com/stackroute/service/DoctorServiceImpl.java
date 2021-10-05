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
public class DoctorServiceImpl implements DoctorService{
    private DoctorRepository doctorRepository;
    private RedisTemplate redisTemplate;
    public static final String HASH_KEY="Doctor";

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, RedisTemplate redisTemplate) {
        this.doctorRepository = doctorRepository;
        this.redisTemplate = redisTemplate;
    }



    @Override
    public Doctor saveDoctorMongoDB(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorByEmailId(String emailId) throws DoctorNotFoundException {
        Doctor doctor = doctorRepository.findByEmailId(emailId);
        if(doctor == null)
        {
            throw new DoctorNotFoundException("Doctor not found");
        }
        else{
            return doctor;
        }
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public void saveDoctorRedis(String emailId) throws DoctorNotFoundException, DoctorAlreadyPresentException {
        Doctor doctor = doctorRepository.findByEmailId(emailId);
//        if(doctor == null)
//        {
//            throw new DoctorNotFoundException("Doctor not found");
//        }
//        else{
//            if(redisTemplate.opsForSet().isMember(HASH_KEY, emailId))
//            {
//                throw new DoctorAlreadyPresentException("Doctor already active");
//            }else{
//                redisTemplate.opsForHash().put(HASH_KEY, doctor.getEmailId(),doctor);}
//        }
        redisTemplate.opsForHash().put(HASH_KEY, doctor.getEmailId(),doctor);

}

    @Override
    public void deleteDoctorRedis(String emailId) throws DoctorNotFoundException {
        if(!redisTemplate.opsForHash().hasKey(HASH_KEY, emailId))
        {
            throw new DoctorNotFoundException("Doctor not found");
        }
        else{
            redisTemplate.opsForHash().delete(HASH_KEY, emailId);
        }
    }

    @Override
    public List<Doctor> getAllDoctorRedis() {
        return redisTemplate.opsForHash().values(HASH_KEY);
    }
}
