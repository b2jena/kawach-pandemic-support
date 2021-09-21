package com.stackroute.usermanagementservice.service;

import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Doctor;

public interface DoctorService {
    Doctor saveDoc(Doctor doctor) throws UserAlreadyExistsException;
}
