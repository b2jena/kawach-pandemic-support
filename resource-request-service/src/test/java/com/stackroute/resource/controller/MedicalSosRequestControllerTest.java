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

/*This is to test the Medical SOS Request Controller class */
@ExtendWith(MockitoExtension.class)
class MedicalSosRequestControllerTest {
    private MockMvc mockMvc;

    /*this is to mock the Medical SOS Request Service Implementation class in this test file*/
    @Mock
    MedicalSosRequestServiceImpl messageService;
    @InjectMocks
    private MedicalSosRequestController messageController;

    private MedicalSosRequest medicalSosRequest;
    private Requirement requirment;
    private List<MedicalSosRequest> messageModelList;

    /*This will run before each test.*/
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
        medicalSosRequest = new MedicalSosRequest();
        requirment = new Requirement();
        medicalSosRequest.setRequestId(UUID.randomUUID());
        medicalSosRequest.setStrCity("Durgapur");
        medicalSosRequest.setStrPatientName("Bikash");
        medicalSosRequest.setStrPhoneNo("9832124814");
        medicalSosRequest.setStrEmail("b2j1999@gmail.com");
        medicalSosRequest.setStrHospitalised("Hospitalised");
        medicalSosRequest.setStrRequestStatus("Medicine");
        medicalSosRequest.setStrFormStatus("Open");
        requirment.setStrRequirementName("DOLO650");
        requirment.setStrRequirementQuantity("5");
        requirment.setStrUnitOfMeasure("Files");
        ArrayList<Requirement> list = new ArrayList<Requirement>();
        list.add(requirment);
        list.add(new Requirement("Paracitemol", "20", "Pices"));
        medicalSosRequest.setArrRequirement(list);
        messageModelList = new ArrayList<>();
        messageModelList.add(medicalSosRequest);
    }

    /*This will run after each test.*/
    @AfterEach
    public void tearDown() {
        medicalSosRequest = null;
    }

    /*This test is to save a Medical SOS Request after passing a valid SOS Request*/
    @Test
    public void givenMedicalSosRequestToSaveThenShouldReturnSavedMedicalSosRequest() throws Exception {
        when(messageService. saveSosRequest(any())).thenReturn(medicalSosRequest);
        mockMvc.perform(post("/api/v1/resource/sos/createSos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(medicalSosRequest)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(messageService).saveSosRequest(any());
    }

/*This is responsible for converting the JSON data in to String data Format*/
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}