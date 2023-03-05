package com.stackroute.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.resource.model.Equipments;
import com.stackroute.resource.service.EquipmentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EquipmentControllerTest {
    @Mock
    EquipmentService equipmentService;
    private MockMvc mockMvc;
    @InjectMocks
    private EquipmentController equipmentController;

    private Equipments equipments;
    private List<Equipments> equipmentsList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(equipmentController).build();
        equipments = new Equipments();
        equipments.setEquipmentId(UUID.randomUUID());
        equipments.setEquipmentName("DemoEquipment");
        equipments.setCity("DemoCity");
        equipments.setHospital("DemoHospital");
        equipments.setAddress("SampleAddressforTesting");
        equipments.setContactPerson("Bikash Jena");
        equipments.setMobileNumber("8327750692");
        equipments.setVerificationStatus(true);
        equipmentsList = new ArrayList<>();
        equipmentsList.add(equipments);
    }

    @AfterEach
    public void tearDown() {
        equipments = null;
    }

    @Test
    void givenEquipmentToSaveThenShouldReturnSavedEquipment() throws Exception {
        when(equipmentService.saveEquipment(any())).thenReturn(equipments);
        mockMvc.perform(post("/equipment/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(equipments)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(equipmentService).saveEquipment(any());
    }

    @Test
    public void givenGetAllEquipmentsThenShouldReturnListOfAllEquipments() throws Exception {
        when(equipmentService.getAllEquipments()).thenReturn(equipmentsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/equipment/getAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(equipments)))
                .andDo(MockMvcResultHandlers.print());
        verify(equipmentService).getAllEquipments();
        verify(equipmentService, times(1)).getAllEquipments();
    }

    @Test
    void getUnverifiedBed() {
    }

    @Test
    void verifyEquipment() {
    }
}