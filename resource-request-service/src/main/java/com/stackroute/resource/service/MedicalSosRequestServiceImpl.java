package com.stackroute.resource.service;

import com.stackroute.resource.model.MedicalSosRequest;
import com.stackroute.resource.repository.MedicalSosRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedicalSosRequestServiceImpl implements MedicalSosRequestService {
    private MedicalSosRequestRepository medicalSosRequestRepository;
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    public MedicalSosRequestServiceImpl(MedicalSosRequestRepository medicalSosRequestRepository) {
        this.medicalSosRequestRepository = medicalSosRequestRepository;
    }
    @Override
    public MedicalSosRequest saveSosRequest(MedicalSosRequest medicalSosRequest) {
        medicalSosRequest.setId(UUID.randomUUID());
        return medicalSosRequestRepository.save(medicalSosRequest);
    }
    @Override
    public List<MedicalSosRequest> getSosRequest() {
        return (List<MedicalSosRequest>) medicalSosRequestRepository.findAll();
    }
}
