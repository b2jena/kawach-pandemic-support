package com.stackroute.service;

import com.stackroute.exception.NoEmailException;
import com.stackroute.model.Patient;
import com.stackroute.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.mail.MessagingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class PatientServiceITest {
    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;
    private List<Patient> userList;
    private Patient user;

    @Test
    public void givenUserToSaveShouldReturnSavedUser() throws NoEmailException {
        Patient user = new Patient("godwinkhalko@gmail.com");
        when(patientRepository.save(any())).thenReturn(user);
        patientService.saveUser(user);
        verify(patientRepository, times(1)).save(any());
    }

    }