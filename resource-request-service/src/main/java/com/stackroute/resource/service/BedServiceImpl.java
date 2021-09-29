package com.stackroute.resource.service;

import com.stackroute.resource.exception.NullValueException;
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
    public Beds saveBed(Beds beds) throws NullValueException {
        if (beds.getBedType() == null || beds.getAddress() == null || beds.getCity() == null || beds.getContactPerson() == null || beds.getMobileNumber() == null) {
            throw new NullValueException();
        } else if (beds.getBedType().equals("") || beds.getAddress().equals("") || beds.getCity().equals("") || beds.getContactPerson().equals("") || beds.getMobileNumber().equals("")) {
            throw new NullValueException();
        } else {
            beds.setBedId(UUID.randomUUID());
            return bedRepository.save(beds);
        }
    }
    @Override
    public List<Beds> getAllBeds() {
        return (List<Beds>) bedRepository.findAll();
    }
}
