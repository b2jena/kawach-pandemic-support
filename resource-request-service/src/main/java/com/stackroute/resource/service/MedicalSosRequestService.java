package com.stackroute.resource.service;

import com.stackroute.resource.model.MedicalSosRequest;

import java.util.List;
import java.util.UUID;

public interface MedicalSosRequestService {

    MedicalSosRequest saveSosRequest(MedicalSosRequest medicalSosRequest);
    List<MedicalSosRequest> getSosRequest();
    void updateSosRequest(UUID id);
}
