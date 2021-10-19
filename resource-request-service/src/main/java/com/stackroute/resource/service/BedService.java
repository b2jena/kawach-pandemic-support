package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Beds;

import java.util.List;
import java.util.UUID;

/*This is the BedService class where abstract methods are declared*/
public interface BedService {
    /*This method will save bed in the mongoDB database */
    Beds saveBed(Beds beds) throws NullValueException;

    List<Beds> getAllBeds();

    Beds getUnverifiedBed();

    List<Beds> getAllBedsByCity(String city, String requirement);

    void updateBed(UUID bedId);


}
