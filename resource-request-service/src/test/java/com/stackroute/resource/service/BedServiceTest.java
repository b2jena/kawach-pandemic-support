package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Beds;
import com.stackroute.resource.repository.BedRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * This class should test the service layer and you should write unit tests
 * for saving, fetching, deleting and updating a bed
 */
@ExtendWith(MockitoExtension.class)
public class BedServiceTest {
    @Mock
    private BedRepository bedRepository;

    @InjectMocks
    private BedServiceImpl bedService;
    private Beds beds, beds1, beds2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        beds = new Beds(UUID.randomUUID(), "SampleBeds", "BikashJena", "SampleBedsforTesting", "sample0", "8327750692", true);
        beds1 = new Beds(UUID.randomUUID(), "Beds 1", "John", "Sample Beds 1 for Testing", "sample1", "8327750692", false);
        beds2 = new Beds(UUID.randomUUID(), "ICU", "Jharsuguda", "Central Hospital", "Bikash Jena", "8327750692", true);
    }

    @AfterEach
    public void tearDown() {
        beds = null;
        beds1 = null;
        beds2 = null;
    }

    @Test
    public void givenBedToSaveThenShouldReturnSavedBed() throws NullValueException {
        when(bedRepository.save(any())).thenReturn(beds);
        assertEquals(beds, bedService.saveBed(beds));
        verify(bedRepository, times(1)).save(any());
    }

    @Test
    public void givenBedToSaveThenShouldReturnSavedBed1() throws NullValueException {
        when(bedRepository.save(any())).thenReturn(beds1);
        assertEquals(beds1, bedService.saveBed(beds1));
        verify(bedRepository).save(any());
    }

    @Test
    public void givenBedToSaveThenShouldReturnSavedBed2() throws NullValueException {
        when(bedRepository.save(any())).thenReturn(beds2);
        assertEquals(beds2, bedService.saveBed(beds2));
        verify(bedRepository, times(1)).save(any());
    }
}
