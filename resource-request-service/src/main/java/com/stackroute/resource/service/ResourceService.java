package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Resources;

import java.util.List;
import java.util.UUID;

public interface ResourceService {
    Resources saveResource(Resources resources) throws NullValueException;

    List<Resources> getAllResources();

    Resources updateResource(Resources resources);

    List<Resources> getAllMedicine(String city);

    List<Resources> getAllEquipment(String City);

    List<Resources> getAllBeds(String City);

    Resources getUnverifiedResources();

    void UpdateMedicine(UUID medId);
}
