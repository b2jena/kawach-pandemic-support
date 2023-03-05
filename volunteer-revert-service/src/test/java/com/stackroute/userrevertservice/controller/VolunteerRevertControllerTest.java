package com.stackroute.userrevertservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userrevertservice.model.Volunteer;
import com.stackroute.userrevertservice.model.VolunteerIncoming;
import com.stackroute.userrevertservice.service.VolunteerRevertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VolunteerRevertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private VolunteerRevertService volunteerRevertService;
    private Volunteer volunteer;
    private List<Volunteer> volunteerList;
    private VolunteerIncoming volunteerIncoming;

    @InjectMocks
    private VolunteerRevertController volunteerRevertController;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setUp() {
        volunteer = new Volunteer("abc@email.com", 700, "Level-2");
        volunteerIncoming = new VolunteerIncoming("xyz@gmail.com", "xyz_type");
        mockMvc = MockMvcBuilders.standaloneSetup(volunteerRevertController).build();
    }

    @Test
    public void givenVolunteerToSaveShouldReturnMessage() throws Exception {
        mockMvc.perform(post("/api/v1/volunteer/revert")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(volunteerIncoming)))
                .andExpect(status().isCreated());
        verify(volunteerRevertService, times(1)).volunteerRevertUpdate(any());
    }

    @Test
    public void givenGetAllVolunteersThenShouldReturnListOfAllVolunteers() throws Exception {
        when(volunteerRevertService.getAllVolunteers()).thenReturn(volunteerList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/volunteer/volunteers")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(volunteer)))
                .andDo(MockMvcResultHandlers.print());
        verify(volunteerRevertService).getAllVolunteers();
        verify(volunteerRevertService, times(1)).getAllVolunteers();
    }


}