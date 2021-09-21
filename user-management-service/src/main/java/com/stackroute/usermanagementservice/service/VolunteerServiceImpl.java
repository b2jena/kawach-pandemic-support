package com.stackroute.usermanagementservice.service;

import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Volunteer;
import com.stackroute.usermanagementservice.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerServiceImpl implements VolunteerService {

    private VolunteerRepository volunteerRepository;

    @Autowired
    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public Volunteer saveVolunteer(Volunteer volunteer) throws UserAlreadyExistsException {
        if (volunteerRepository.existsById(volunteer.getEmailId())) {
            throw new UserAlreadyExistsException();
        }
        return volunteerRepository.save(volunteer);
    }
}
