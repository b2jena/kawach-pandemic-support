package com.stackroute.repo;

import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    Doctor findById(int id);

    List<Doctor> findByStatus(int status) throws DoctorNotFoundException;
}
