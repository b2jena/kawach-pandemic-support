package com.stackroute.usermanagementservice.service;

import com.stackroute.usermanagementservice.exception.NullValueException;
import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Doctor;
import com.stackroute.usermanagementservice.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*Implementation class implementing the necessary methods of doctor Service*/
@Service
public class DoctorServiceImpl implements DoctorService {

    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class.getName());

    private DoctorRepository doctorRepository;

    /*Doctor Repository is injected in this Doctor Service Implementation class by @Autowired annotation*/
    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    /*Method to store the doctor details into the mongoDB database*/

    @Override
    public Doctor saveDoctor(Doctor doctor) throws UserAlreadyExistsException, NullValueException, Exception {
        try {
            if (doctor.getEmailId() == null || doctor.getPassword() == null || doctor.getFullName() == null || doctor.getPhoneNumber() == 0 || doctor.getMedicalRegistrationId() == null || doctor.getSpecialization() == null) {
                throw new NullValueException();
            } else if (doctor.getEmailId().equals("") || doctor.getPassword().equals("") || doctor.getFullName().equals("") || doctor.getMedicalRegistrationId().equals("") || doctor.getSpecialization().equals("")) {
                throw new NullValueException();
            } else if (doctorRepository.existsById(doctor.getEmailId())) {
                throw new UserAlreadyExistsException();
            }
            Doctor savedDoctor = doctorRepository.save(doctor);

            return savedDoctor;
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            throw new Exception();
        }
    }

    /*Method to fetch a specific Doctor's details by the email id of the doctor*/
    @Override
    public Doctor getDoctorById(String id) throws Exception {
        try {
            Optional<Doctor> optional = doctorRepository.findById(id);
            if (optional.isPresent()) {
                return optional.get();
            }
            return null;
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            throw new Exception();
        }
    }
}
