package com.stackroute.usermanagementservice.service;

import com.stackroute.usermanagementservice.exception.NullValueException;
import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Volunteer;

/*Interface containing the methods that needs to be implemented in the implementation class*/

public interface VolunteerService {
    Volunteer saveVolunteer(Volunteer volunteer) throws UserAlreadyExistsException, NullValueException;
}
