package com.stackroute.userrevertservice.service;

import com.stackroute.userrevertservice.model.Volunteer;
import com.stackroute.userrevertservice.model.VolunteerIncoming;
import com.stackroute.userrevertservice.repository.VolunteerRevertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerRevertServiceImpl implements VolunteerRevertService {

    private VolunteerRevertRepository volunteerRevertRepository;

    @Autowired
    public VolunteerRevertServiceImpl(VolunteerRevertRepository volunteerRevertRepository) {
        this.volunteerRevertRepository = volunteerRevertRepository;
    }

    @Override
    public void saveVolunteer(Volunteer volunteer) {
        volunteerRevertRepository.save(volunteer);
    }

    @Override
    public List<Volunteer> getAllVolunteers() {
        return (List<Volunteer>) volunteerRevertRepository.findAll();
    }

    @Override
    public void volunteerRevertUpdate(VolunteerIncoming volunteerIncoming) {
        Volunteer updatedVolunteer = new Volunteer("", 0, "");
        int updatedScore = 0;

        Optional<Volunteer> optional = volunteerRevertRepository.findById(volunteerIncoming.getEmailId());
        System.out.println("details:"+optional);
        if (optional.isPresent()) {
            updatedScore = volunteerRevertRepository.findById(volunteerIncoming.getEmailId()).get().getScore() + Score(volunteerIncoming.getType());
            updatedVolunteer = volunteerRevertRepository.findById(volunteerIncoming.getEmailId()).get();
            updatedVolunteer.setScore(updatedScore);
            updatedVolunteer.setLevel(Level(updatedScore));
            saveVolunteer(updatedVolunteer);


        } else {
            updatedScore = Score(volunteerIncoming.getType());

            updatedVolunteer.setScore(updatedScore);
            updatedVolunteer.setLevel(Level(updatedScore));
            updatedVolunteer.setVolunteerEmailId(volunteerIncoming.getEmailId());
            saveVolunteer(updatedVolunteer);

        }

    }


    public int Score(String type) {
        int score = 0;

        if (type.equalsIgnoreCase("Bed_Request_Closure")) {
            score += 30;
        } else if (type.equalsIgnoreCase("Equipment_Request_Closure")) {
            score += 50;
        } else if (type.equalsIgnoreCase("Medicine_Request_Closure")) {
            score += 10;
        } else if (type.equalsIgnoreCase("Bed_Request_Pass")) {
            score += 3;
        } else if (type.equalsIgnoreCase("Equipment_Request_Pass")) {
            score += 5;
        } else if (type.equalsIgnoreCase("Medicine_Request_Pass")) {
            score += 1;
        } else if (type.equalsIgnoreCase("Create_Bed_Resource")) {
            score += 300;
        } else if (type.equalsIgnoreCase("Create_Equipment_Resource")) {
            score += 500;
        } else if (type.equalsIgnoreCase("Create_Medicine_Resource")) {
            score += 100;
        } else if (type.equalsIgnoreCase("Verify_Bed_Resource")) {
            score += 100;
        } else if (type.equalsIgnoreCase("Verify_Equipment_Resource")) {
            score += 50;
        } else if (type.equalsIgnoreCase("Verify_Medicine_Resource")) {
            score += 30;
        }

        return score;
    }

    public String Level(int updatedScore) {
        String level = "";
        if (updatedScore > 0 && updatedScore <= 500) {
            level = "Level-1";
        } else if (updatedScore > 500 && updatedScore <= 1000) {
            level = "Level-2";
        } else if (updatedScore > 1000 && updatedScore <= 2000) {
            level = "Level-3";
        } else if (updatedScore > 2000 && updatedScore <= 5000) {
            level = "Golden_Volunteer";
        } else if (updatedScore > 5000) {
            level = "Angel_Volunteer";
        }
        return level;
    }
}



