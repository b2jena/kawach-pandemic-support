package com.stackroute.resource.service;

import com.stackroute.resource.model.MedicalSosRequest;

import java.util.List;
import java.util.UUID;


public interface MedicalSosRequestService {

    MedicalSosRequest saveSosRequest(MedicalSosRequest medicalSosRequest);

    List<MedicalSosRequest> getSosRequest();

    List<MedicalSosRequest> getMedSOS();

    List<MedicalSosRequest> getEquipSOS();

    List<MedicalSosRequest> getBedSOS();

    void updateSosRequest(UUID id);

    MedicalSosRequest getSOSMed();

    MedicalSosRequest getSOSEquip();

    MedicalSosRequest getSOSBed();

    void closeSOS(UUID requestId);
}
