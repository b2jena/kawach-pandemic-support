package com.stackroute.usermanagementservice.service;

import com.stackroute.usermanagementservice.exception.NullValueException;
import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Doctor;

/*Interface containing the methods that needs to be implemented in the implementation class*/

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor) throws UserAlreadyExistsException, NullValueException;

    Doctor getDoctorById(String id);
}
