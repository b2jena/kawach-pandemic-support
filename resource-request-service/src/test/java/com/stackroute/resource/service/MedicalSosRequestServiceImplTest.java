package com.stackroute.resource.service;

import com.stackroute.resource.model.MedicalSosRequest;
import com.stackroute.resource.model.Requirement;
import com.stackroute.resource.repository.MedicalSosRequestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/*This is to test the Medical SOS Request Service Implementation class */
@ExtendWith(MockitoExtension.class)
class MedicalSosRequestServiceImplTest {

    /*This is to mock the mongoDB repository*/
    @Mock
    private MedicalSosRequestRepository repository;

    @InjectMocks
    private MedicalSosRequestServiceImpl messageService;
    private MedicalSosRequest medicalSosRequest;
    private Requirement requirement;
    private List<MedicalSosRequest> MessageList;
    private Optional optional;
    SequenceGeneratorService sequenceGeneratorService;

    /*This will run before each test.*/
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        requirement = new Requirement("DOLO 650", "one", "File");
        ArrayList<Requirement> list = new ArrayList<Requirement>();
        list.add(requirement);
        requirement = new Requirement("DOLO 650", "two", "File");
        list.add(requirement);
        medicalSosRequest = new MedicalSosRequest(UUID.randomUUID(), "Debjit", "Male", "9832124814", "dmandal9832124814@gmial.com", "Hospitalised", "Durgapur", list , "Medicine", "Open");
        optional = Optional.of(medicalSosRequest);
    }

    /*This will run after each test.*/
    @AfterEach
    public void tearDown() {
        medicalSosRequest = new MedicalSosRequest();
    }

    /*This test is to save a Medical SOS Request after passing a valid SOS Request*/
    @Test
    void givenMedicalSosRequestToSaveThenShouldReturnSavedMedicalSosRequest() throws Exception {
        when(repository.save(any())).thenReturn(medicalSosRequest);
        assertEquals(medicalSosRequest, messageService.saveSosRequest(medicalSosRequest))  ;
        verify(repository, times(1)).save(any());
    }
}