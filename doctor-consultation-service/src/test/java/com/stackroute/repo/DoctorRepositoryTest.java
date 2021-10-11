package com.stackroute.repo;

import com.stackroute.model.Doctor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@DataMongoTest
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository userRepository;

    @Test
    public void givenUserToSaveShouldReturnSavedUser()
    {
        Doctor user = new Doctor("godwinkhalko2@gmail.com", "Godwin Khalko", "Dermatologist");
        userRepository.save(user);
        Doctor user1 = userRepository.findByEmailId(user.getEmailId());
        assertNotNull(user1);
        assertEquals(user.getFullName(), user1.getFullName());
    }

    @Test
    public void givenGetALlUsersShouldReturnListOfAllUsers()
    {
        Doctor user1 = new Doctor("godwinkhalko2@gmail.com", "Godwin Khalko", "Dermatologist");
        Doctor user2 = new Doctor("rajeshkumar@gmail.com", "Rajesh Kumar", "Cardiologist");

        userRepository.save(user1);
        userRepository.save(user2);

        List<Doctor> userList = (List<Doctor>)userRepository.findAll();
        assertEquals("Godwin Khalko", userList.get(1).getFullName());
    }

}