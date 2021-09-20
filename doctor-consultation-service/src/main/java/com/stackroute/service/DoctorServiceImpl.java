package com.stackroute.service;

import com.stackroute.model.Doctor;
import com.stackroute.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    private DoctorRepo doctorRepo;

    @Autowired
    public DoctorServiceImpl(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }
    @PersistenceContext
    EntityManager em;

    @Override
    public Doctor setStatus(Doctor doctor, int status) {
        doctor.setStatus(status);
        return doctor;
    }

    @Override
    public List<Doctor> findByStatus() {
        Query q = em.createQuery("select e from Doctor e where e.status = 1");
        List<Doctor> list=q.getResultList();
        return list;
    }
}
