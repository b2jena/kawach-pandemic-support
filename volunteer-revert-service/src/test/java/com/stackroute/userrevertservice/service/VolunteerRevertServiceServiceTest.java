package com.stackroute.userrevertservice.service;

import com.stackroute.userrevertservice.model.Volunteer;
import com.stackroute.userrevertservice.model.VolunteerIncoming;
import com.stackroute.userrevertservice.repository.VolunteerRevertRepository;
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
class VolunteerRevertServiceServiceTest {

    @Mock
    private VolunteerRevertRepository volunteerRevertRepository;

    @InjectMocks
    private VolunteerRevertServiceImpl volunteerRevertService;
    private Volunteer volunteer;
    private VolunteerIncoming volunteerIncoming;
    private List<Volunteer> volunteerList;

    @Test
    public void givenVolunteerToSave() throws Exception {
        Volunteer volunteer = new Volunteer("abc@email.com", 700, "Level-2");
        volunteerRevertService.saveVolunteer(volunteer);
        verify(volunteerRevertRepository, times(1)).save(any());
    }

    @Test
    public void givenGetAllVolunteersShouldReturnListOfAllVolunteers() throws Exception {
        volunteerRevertRepository.save(volunteer);
        when(volunteerRevertRepository.findAll()).thenReturn(volunteerList);
        List<Volunteer> volunteerList = volunteerRevertService.getAllVolunteers();
        assertEquals(volunteerList, volunteerList);
        verify(volunteerRevertRepository, times(1)).save(volunteer);
        verify(volunteerRevertRepository, times(1)).findAll();

    }

    @Test
    public void givenVolunteerRevertUpdate() throws Exception {
        VolunteerIncoming volunteerIncoming = new VolunteerIncoming("xyz@gmail.com", "xyz_type");
        volunteerRevertService.volunteerRevertUpdate(volunteerIncoming);
        verify(volunteerRevertRepository, times(1)).findById(volunteerIncoming.getEmailId());

    }

}