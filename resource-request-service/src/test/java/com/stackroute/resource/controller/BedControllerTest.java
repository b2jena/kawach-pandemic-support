package com.stackroute.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.resource.model.Beds;
import com.stackroute.resource.service.BedService;
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

/**
 * This class should test the controller layer and you should write unit tests
 * for saving, fetching, deleting and updating a bed
 */
@ExtendWith(MockitoExtension.class)
public class BedControllerTest {
    @Mock
    BedService bedService;
    private MockMvc mockMvc;
    @InjectMocks
    private BedController bedController;

    private Beds beds;
    private List<Beds> bedsList;

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
        mockMvc = MockMvcBuilders.standaloneSetup(bedController).build();
        beds = new Beds();
        beds.setBedId(UUID.fromString("c81d4e2e-bcf2-11e6-869b-7df92533d2db"));
        beds.setBedType("DemoBed");
        beds.setCity("DemoCity");
        beds.setAddress("SampleBedforTesting");
        beds.setContactPerson("Bikash Jena");
        beds.setMobileNumber("8327750692");
        beds.setVerificationStatus(true);
        bedsList = new ArrayList<>();
        bedsList.add(beds);
    }

    @AfterEach
    public void tearDown() {
        beds = null;
    }

    @Test
    public void givenBedToSaveThenShouldReturnSavedBed() throws Exception {
        when(bedService.saveBed(any())).thenReturn(beds);
        mockMvc.perform(post("/bed/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(beds)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(bedService).saveBed(any());
    }

    @Test
    public void givenGetAllBedsThenShouldReturnListOfAllBeds() throws Exception {
        when(bedService.getAllBeds()).thenReturn(bedsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/bed/getAll")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(beds)))
                .andDo(MockMvcResultHandlers.print());
        verify(bedService).getAllBeds();
        verify(bedService, times(1)).getAllBeds();
    }
}
