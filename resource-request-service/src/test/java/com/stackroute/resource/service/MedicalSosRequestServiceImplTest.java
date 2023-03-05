package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
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


@ExtendWith(MockitoExtension.class)
class MedicalSosRequestServiceImplTest {

    SequenceGeneratorService sequenceGeneratorService;
    @Mock
    private MedicalSosRequestRepository repository;
    @InjectMocks
    private MedicalSosRequestServiceImpl messageService;
    private MedicalSosRequest medicalSosRequest;
    private Requirement requirement;
    private List<MedicalSosRequest> MessageList;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        requirement = new Requirement("DOLO 650", "one", "File");
        ArrayList<Requirement> list = new ArrayList<Requirement>();
        list.add(requirement);
        requirement = new Requirement("DOLO 650", "two", "File");
        list.add(requirement);
        medicalSosRequest = new MedicalSosRequest(UUID.randomUUID(), "Debjit", "Male", "9832124814", "dmandal9832124814@gmial.com", "Hospitalised", "Durgapur", list, "Medicine", "Open");
        optional = Optional.of(medicalSosRequest);
    }

    @AfterEach
    public void tearDown() {
        medicalSosRequest = new MedicalSosRequest();
    }

    @Test
    void givenMessageModelToSaveThenShouldReturnSavedMessageModel() throws NullValueException {
        when(repository.save(any())).thenReturn(medicalSosRequest);
        assertEquals(medicalSosRequest, messageService.saveSosRequest(medicalSosRequest));
        verify(repository, times(1)).save(any());
    }
}