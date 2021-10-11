package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.model.Doctor;
import com.stackroute.repo.DoctorRepository;
import com.stackroute.service.DoctorService;
import com.stackroute.service.DoctorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DoctorService userService;
    private Doctor user;
    private List<Doctor> userList;

    @InjectMocks
    private DoctorController userController;

    @BeforeEach
    public void setUp() {
        user = new Doctor("godwinkhalko2@gmail.com", "Godwin Khalko", "Dermatologist");
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void givenUserToSaveShouldReturnSavedUser() throws Exception {
        when(userService.saveDoctorMongoDB(any())).thenReturn(user);
        mockMvc.perform(post("/api/v1/doctorm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isCreated());
        verify(userService, times(1)).saveDoctorMongoDB(any());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


