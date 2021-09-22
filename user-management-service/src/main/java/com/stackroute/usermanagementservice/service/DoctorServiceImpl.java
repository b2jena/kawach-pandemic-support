package com.stackroute.usermanagementservice.service;

import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Doctor;
import com.stackroute.usermanagementservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor saveDoc(Doctor doctor) throws UserAlreadyExistsException {
        if (doctorRepository.existsById(doctor.getEmailId())) {
            throw new UserAlreadyExistsException();
        }
        Doctor savedDoc = doctorRepository.save(doctor);

        return savedDoc;
    }
}
