package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.Equipments;

import java.util.List;

public interface EquipmentService {
    Equipments saveEquipment(Equipments resources) throws NullValueException;
    List<Equipments> getAllEquipments();
    Equipments getUnverifiedEquipments();
}
