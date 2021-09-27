package com.stackroute.resource.service;

import com.stackroute.resource.model.Equipments;

import java.util.List;

public interface EquipmentService {
    Equipments saveEquipment(Equipments resources);
    List<Equipments> getAllEquipments();
}
