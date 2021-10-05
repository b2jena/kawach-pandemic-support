package com.stackroute.repo;

import com.stackroute.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DoctorRepository {

    void save(Doctor doctor);

    Set<Doctor> findAll();

    Doctor findByEmail(String id);

    List<Doctor> findByStatus(int status);

    void updateStatus(Doctor doctor);

}
