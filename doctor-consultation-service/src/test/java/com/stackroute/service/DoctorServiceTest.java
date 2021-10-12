package com.stackroute.service;

import com.stackroute.exception.DoctorNotFoundException;
import com.stackroute.model.Doctor;
import com.stackroute.repo.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {
    @Mock
    private DoctorRepository userRepository;

    @InjectMocks
    private DoctorServiceImpl userService;
    private List<Doctor> userList;
    private Doctor user;

    @Test
    public void givenUserToSaveInMongoDBShouldReturnSavedUser()
    {
        Doctor user1 = new Doctor("godwinkhalko2@gmail.com", "Godwin Khalko", "Dermatologist");
        when(userRepository.save(any())).thenReturn(user);
        userService.saveDoctorMongoDB(user);
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void givenGetAllUsersInMongoDBShouldReturnListOfAllUsers() {
        userRepository.save(user);
        when(userRepository.findAll()).thenReturn(userList);
        List<Doctor> userlist = userService.getAllDoctors();
        assertEquals(userlist, userlist);
        verify(userRepository,times(1)).save(user);
        verify(userRepository,times(1)).findAll();
    }

    @Test
    public void givenGetDoctorByEmailIDshouldReturnDoctorWithEmailID() throws DoctorNotFoundException {
        when(userRepository.findByEmailId("godwinkhalko2@gmail.com")).thenReturn(new Doctor("godwinkhalko2@gmail.com", "Godwin Khalko", "Dermatologist"));
        Doctor user3 = userService.getDoctorByEmailId("godwinkhalko2@gmail.com");
        assertEquals("godwinkhalko2@gmail.com", user3.getEmailId());
        assertEquals("Godwin Khalko", user3.getFullName());
        assertEquals("Dermatologist", user3.getSpecialization());
    }

}