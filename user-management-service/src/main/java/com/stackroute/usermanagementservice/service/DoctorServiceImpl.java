package com.stackroute.usermanagementservice.service;

import com.stackroute.usermanagementservice.exception.NullValueException;
import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Doctor;
import com.stackroute.usermanagementservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*Implementation class implementing the necessary methods of doctor Service*/
@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    /*Method to store the doctor data into the database*/

    @Override
    public Doctor saveDoctor(Doctor doctor) throws UserAlreadyExistsException, NullValueException {
        if (doctorRepository.existsById(doctor.getEmailId())) {
            throw new UserAlreadyExistsException();
        } else if (doctor.getEmailId() == null || doctor.getPassword() == null || doctor.getFullName() == null || doctor.getPhoneNumber() == 0 || doctor.getMedicalRegistrationId() == null || doctor.getSpecialization() == null) {
            throw new NullValueException();
        }
        Doctor savedDoctor = doctorRepository.save(doctor);

        return savedDoctor;
    }
}
