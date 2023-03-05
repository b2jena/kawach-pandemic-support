package com.stackroute.service;

import com.stackroute.entity.Patient;
import com.stackroute.repository.PatientRepo;
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
class PatientServiceITest {
    @Mock
    private PatientRepo patientRepo;

    @InjectMocks
    private PatientServiceImpl patientService;
    private List<Patient> userList;
    private Patient user;

    @Test
    public void givenUserToSaveShouldReturnSavedUser() {
        Patient user = new Patient("godwinkhalko@gmail.com");
        when(patientRepo.save(any())).thenReturn(user);
        patientService.saveUser(user);
        verify(patientRepo, times(1)).save(any());
    }

    @Test
    public void givenGetAllUsersShouldReturnListOfAllUsers() {
        patientRepo.save(user);
        when(patientRepo.findAll()).thenReturn(userList);
        List<Patient> userlist = patientService.getAll();
        assertEquals(userlist, userlist);
        verify(patientRepo, times(1)).save(user);
        verify(patientRepo, times(1)).findAll();
    }
}