package com.stackroute.service;

import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;
import com.stackroute.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepo doctorRepo;

    @Autowired
    public DoctorServiceImpl(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public String changeStatus(int id) throws DoctorNotFoundException {
        Doctor doctor = doctorRepo.findById(id);
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor not found");
        } else {
            if (doctor.getStatus() == 0) {
                doctor.setStatus(1);
            } else {
                doctor.setStatus(0);
            }
            return "Status Changed";
        }
    }

    @Override
    public Doctor findById(int id) {
        return doctorRepo.findById(id);
    }

    @Override
    public List<Doctor> findByStatus(int status) throws DoctorNotFoundException {
        List<Doctor> list = doctorRepo.findByStatus(status);
        if (list == null) {
            throw new DoctorNotFoundException("Not Doctors available");
        }
        return list;

    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    @Override
    public List<Doctor> getAll() {
        return (List<Doctor>) doctorRepo.findAll();
    }
}
