package com.stackroute.resource.service;

import com.stackroute.resource.model.Beds;
import com.stackroute.resource.repository.BedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BedServiceImpl implements BedService{
    private BedRepository bedRepository;
    SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    public BedServiceImpl(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }
    @Override
    public Beds saveBed(Beds beds) {
        beds.setId(UUID.randomUUID());
        return bedRepository.save(beds);
    }
    @Override
    public List<Beds> getAllBeds() {
        return (List<Beds>) bedRepository.findAll();
    }
}
