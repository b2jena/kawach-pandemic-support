package com.stackroute.usermanagementservice.service;

import com.stackroute.usermanagementservice.exception.NullValueException;
import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Volunteer;
import com.stackroute.usermanagementservice.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*Implementation class implementing the necessary methods of volunteer Service*/

@Service
public class VolunteerServiceImpl implements VolunteerService {

    private VolunteerRepository volunteerRepository;

    @Autowired
    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    /*Method to store the volunteer data into the database*/
    @Override
    public Volunteer saveVolunteer(Volunteer volunteer) throws UserAlreadyExistsException, NullValueException {
        if (volunteerRepository.existsById(volunteer.getEmailId())) {
            throw new UserAlreadyExistsException();
        } else if (volunteer.getEmailId() == null || volunteer.getFullName() == null || volunteer.getPassword() == null || volunteer.getPhoneNumber() == 0) {
            throw new NullValueException();
        }
        return volunteerRepository.save(volunteer);
    }
}
