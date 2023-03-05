package com.stackroute.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.resource.model.Resources;
import com.stackroute.resource.service.ResourceService;
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
class ResourceControllerTest {
    @Mock
    ResourceService resourceService;
    @InjectMocks
    ResourceController resourceController;
    private MockMvc mockMvc;
    private Resources resources;
    private List<Resources> resourcesList;

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
        mockMvc = MockMvcBuilders.standaloneSetup(resourceController).build();
        resources = new Resources();
        resources.setId(UUID.randomUUID());
        resources.setMedicineName("DemoMedicine");
        resources.setCity("DemoCity");
        resources.setPharmacy("DemoPharmacy");
        resources.setAddress("SampleAddressforTesting");
        resources.setContactPerson("Bikash Jena");
        resources.setMobileNumber("8327750692");
        resources.setVerificationStatus(true);
        resourcesList = new ArrayList<>();
        resourcesList.add(resources);
    }

    @AfterEach
    public void tearDown() {
        resources = null;
    }

    @Test
    void givenResourceToSaveThenShouldReturnSavedResource() throws Exception {
        when(resourceService.saveResource(any())).thenReturn(resources);
        mockMvc.perform(post("/medicine/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(resources)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(resourceService).saveResource(any());
    }

    @Test
    public void givenGetAllResourcesThenShouldReturnListOfAllResources() throws Exception {
        when(resourceService.getAllResources()).thenReturn(resourcesList);
        mockMvc.perform(MockMvcRequestBuilders.get("/medicine/getAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(resources)))
                .andDo(MockMvcResultHandlers.print());
        verify(resourceService).getAllResources();
        verify(resourceService, times(1)).getAllResources();
    }
}