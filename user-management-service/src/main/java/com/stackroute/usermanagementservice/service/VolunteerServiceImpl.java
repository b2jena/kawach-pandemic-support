package com.stackroute.usermanagementservice.service;

import com.stackroute.usermanagementservice.exception.NullValueException;
import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Volunteer;
import com.stackroute.usermanagementservice.repository.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*Implementation class implementing the necessary methods of volunteer Service*/

@Service
public class VolunteerServiceImpl implements VolunteerService {

    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    Logger logger = LoggerFactory.getLogger(VolunteerServiceImpl.class.getName());


    private VolunteerRepository volunteerRepository;

    /*Volunteer Repository is injected in this Volunteer Service Implementation class by @Autowired annotation*/
    @Autowired
    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    /*Method to store the volunteer data into the database*/
    @Override
    public Volunteer saveVolunteer(Volunteer volunteer) throws UserAlreadyExistsException, NullValueException, Exception {
        try {
            if (volunteer.getEmailId() == null || volunteer.getFullName() == null || volunteer.getPassword() == null || volunteer.getPhoneNumber() == 0) {
                throw new NullValueException();
            } else if (volunteer.getEmailId().equals("") || volunteer.getFullName().equals("") || volunteer.getPassword().equals("")) {
                throw new NullValueException();
            } else if (volunteerRepository.existsById(volunteer.getEmailId())) {
                throw new UserAlreadyExistsException();
            }
            return volunteerRepository.save(volunteer);
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            throw new Exception();
        }
    }
}
