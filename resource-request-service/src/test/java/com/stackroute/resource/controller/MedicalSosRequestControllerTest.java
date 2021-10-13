package com.stackroute.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.resource.model.MedicalSosRequest;
import com.stackroute.resource.model.Requirement;
import com.stackroute.resource.service.MedicalSosRequestServiceImpl;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MedicalSosRequestControllerTest {

    private MockMvc mockMvc;
    @Mock
    MedicalSosRequestServiceImpl messageService;
    @InjectMocks
    private MedicalSosRequestController messageController;

    private MedicalSosRequest medicalSosRequest;
    private Requirement requirment;
    private List<MedicalSosRequest> messageModelList;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
        medicalSosRequest = new MedicalSosRequest();
        requirment = new Requirement();
        medicalSosRequest.setRequestId(UUID.randomUUID());
        medicalSosRequest.setCity("Durgapur");
        medicalSosRequest.setPatientName("Bikash");
        medicalSosRequest.setPhoneNo("9832124814");
        medicalSosRequest.setEmail("b2j1999@gmail.com");
        medicalSosRequest.setHospitalised("Hospitalised");
        medicalSosRequest.setRequestStatus("Medicine");
        medicalSosRequest.setFormStatus("Open");
        requirment.setRequirementName("DOLO650");
        requirment.setRequirementQuantity("5");
        requirment.setUnitOfMeasure("Files");
        ArrayList<Requirement> list = new ArrayList<Requirement>();
        list.add(requirment);
        list.add(new Requirement("Paracitemol", "20", "Pices"));
        medicalSosRequest.setRequirement(list);
        messageModelList = new ArrayList<>();
        messageModelList.add(medicalSosRequest);
    }

    @AfterEach
    public void tearDown() {
        medicalSosRequest = null;
    }

    @Test
    public void givenMessageModelToSaveThenShouldReturnSavedMessageModel() throws Exception {
        when(messageService. saveSosRequest(any())).thenReturn(medicalSosRequest);
        mockMvc.perform(post("/sos/createSos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(medicalSosRequest)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(messageService).saveSosRequest(any());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}