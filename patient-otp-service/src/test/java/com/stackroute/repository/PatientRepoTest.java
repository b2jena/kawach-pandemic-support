package com.stackroute.repository;

import com.stackroute.entity.Patient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PatientRepoTest {
    @Autowired
    private PatientRepo patientRepo;

    @Test
    public void givenUserToSaveShouldReturnSavedUser()
    {
        Patient patient = new Patient("godwinkhalko@gmail.com");
        patientRepo.save(patient);
        Patient patient1 = patientRepo.findById(patient.getEmail()).get();
        assertNotNull(patient1);
        assertEquals(patient.getEmail(), patient1.getEmail());
    }

    @Test
    public void givenGetALlUsersShouldReturnListOfAllUsers()
    {
        Patient patient = new Patient("godwinkhalko@gmail.com");
        Patient patient1 = new Patient("rajesh@gmail.com");

        patientRepo.save(patient);
        patientRepo.save(patient1);

        List<Patient> userList = (List<Patient>)patientRepo.findAll();
        assertEquals("rajesh@gmail.com", userList.get(1).getEmail());
    }


}