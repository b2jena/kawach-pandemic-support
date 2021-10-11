package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
import com.stackroute.resource.model.MedicalSosRequest;
import com.stackroute.resource.model.Resources;
//import com.stackroute.resource.model.Resources;

import java.util.List;
import java.util.UUID;

public interface MedicalSosRequestService {

    MedicalSosRequest saveSosRequest(MedicalSosRequest medicalSosRequest) throws NullValueException;
    List<MedicalSosRequest> getSosRequest();
    List<MedicalSosRequest> getMedSOS();
    List<MedicalSosRequest> getEquipSOS();
    List<MedicalSosRequest> getBedSOS();
    void updateSosRequest(UUID id);
    MedicalSosRequest getSOSMed();
    MedicalSosRequest getSOSEquip();
    MedicalSosRequest getSOSBed();
    void closeSOS(UUID requestId);
//    List<Resources> getAllRes();
}
