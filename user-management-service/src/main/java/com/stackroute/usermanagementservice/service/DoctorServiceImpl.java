package com.stackroute.usermanagementservice.service;

import com.stackroute.usermanagementservice.exception.NullValueException;
import com.stackroute.usermanagementservice.exception.UserAlreadyExistsException;
import com.stackroute.usermanagementservice.model.Doctor;
import com.stackroute.usermanagementservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        if (doctor.getEmailId() == null || doctor.getPassword() == null || doctor.getFullName() == null || doctor.getPhoneNumber() == 0 || doctor.getMedicalRegistrationId() == null || doctor.getSpecialization() == null) {
            throw new NullValueException();
        } else if (doctor.getEmailId().equals("") || doctor.getPassword().equals("") || doctor.getFullName().equals("") || doctor.getMedicalRegistrationId().equals("") || doctor.getSpecialization().equals("")) {
            throw new NullValueException();
        } else if (doctorRepository.existsById(doctor.getEmailId())) {
            throw new UserAlreadyExistsException();
        }
        Doctor savedDoctor = doctorRepository.save(doctor);

        return savedDoctor;
    }

    @Override
    public Doctor getDoctorById(String id) {
        Optional<Doctor> optional = doctorRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
