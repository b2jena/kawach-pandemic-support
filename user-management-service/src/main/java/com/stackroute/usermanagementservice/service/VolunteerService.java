package com.stackroute.usermanagementservice.service;

import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Volunteer;

public interface VolunteerService{
    Volunteer saveVolunteer(Volunteer volunteer) throws UserAlreadyExistsException;
}
