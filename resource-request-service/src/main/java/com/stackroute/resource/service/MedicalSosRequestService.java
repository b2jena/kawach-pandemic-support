package com.stackroute.resource.service;

import com.stackroute.resource.model.MedicalSosRequest;

import java.util.List;
import java.util.UUID;

/*This is the Medical Sos Request Service class where abstract methods are declared*/
public interface MedicalSosRequestService {

    /*This Method will save the SOS Requests in the mongoDB database */
    MedicalSosRequest saveSosRequest(MedicalSosRequest medicalSosRequest) throws Exception;

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
