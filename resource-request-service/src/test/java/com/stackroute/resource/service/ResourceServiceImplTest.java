package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Resources;
import com.stackroute.resource.repository.ResourceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ResourceServiceImplTest {
    @Mock
    private ResourceRepository resourceRepository;

    @InjectMocks
    private ResourceServiceImpl resourceService;
    private Resources resources, resources1, resources2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        resources = new Resources(UUID.randomUUID(), "Paracetamol", "Jharsuguda", "Maa Chandi Medical Store", "SampleAddressforTesting", "sample0", "8327750692", true);
        resources1 = new Resources(UUID.randomUUID(), "Revital H", "Wakanda", "T'Chaka Medical Store", "Sample Resources 1 for Testing", "sample1", "8327750692", false);
        resources2 = new Resources(UUID.randomUUID(), "Crocin", "Jharsuguda", "Pioneer Medical Store", "Central Hospital", "Bikash Jena", "8327750692", true);
    }

    @AfterEach
    public void tearDown() {
        resources = null;
        resources1 = null;
        resources2 = null;
    }

    @Test
    public void givenBedToSaveThenShouldReturnSavedBed() throws NullValueException {
        when(resourceRepository.save(any())).thenReturn(resources);
        assertEquals(resources, resourceService.saveResource(resources))  ;
        verify(resourceRepository, times(1)).save(any());
    }

    @Test
    public void givenBedToSaveThenShouldReturnSavedBed1() throws NullValueException {
        when(resourceRepository.save(any())).thenReturn(resources1);
        assertEquals(resources1, resourceService.saveResource(resources1));
        verify(resourceRepository).save(any());
    }

    @Test
    public void givenBedToSaveThenShouldReturnSavedBed2() throws NullValueException {
        when(resourceRepository.save(any())).thenReturn(resources2);
        assertEquals(resources2, resourceService.saveResource(resources2));
        verify(resourceRepository, times(1)).save(any());
    }
}