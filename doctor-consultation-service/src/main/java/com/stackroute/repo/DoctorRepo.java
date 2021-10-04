package com.stackroute.repo;

import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    String changeStatus(int id) throws DoctorNotFoundException;

    Doctor findById(int id);

    List<Doctor> findByStatus(int status) throws DoctorNotFoundException;

    Doctor saveDoctor(Doctor doctor);

    List<Doctor> getAll();
}
