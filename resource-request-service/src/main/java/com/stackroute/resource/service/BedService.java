package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Beds;

import java.util.List;
import java.util.UUID;

public interface BedService {
    Beds saveBed(Beds beds) throws NullValueException;

    List<Beds> getAllBeds();

    Beds getUnverifiedBed();

    List<Beds> getAllBedsByCity(String city);

    void updateBed(UUID bedId);
}
