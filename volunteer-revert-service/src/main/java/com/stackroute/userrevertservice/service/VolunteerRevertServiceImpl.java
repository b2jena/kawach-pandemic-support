package com.stackroute.userrevertservice.service;

import com.stackroute.userrevertservice.model.Volunteer;
import com.stackroute.userrevertservice.model.VolunteerIncoming;
import com.stackroute.userrevertservice.repository.VolunteerRevertRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*Implementation class implementing the necessary methods of volunteer Service*/
@Service
public class VolunteerRevertServiceImpl implements VolunteerRevertService {

    /*This is to create a logger object by which we can call the functionality of the logger class.*/
    Logger logger = LoggerFactory.getLogger(VolunteerRevertServiceImpl.class.getName());


    private VolunteerRevertRepository volunteerRevertRepository;

    /*Volunteer Revert Repository is injected in this Volunteer Revert Service Implementation class by @Autowired annotation*/
    @Autowired
    public VolunteerRevertServiceImpl(VolunteerRevertRepository volunteerRevertRepository) {
        this.volunteerRevertRepository = volunteerRevertRepository;
    }

    /*Method to save the volunteer details into the database*/
    @Override
    public void saveVolunteer(Volunteer volunteer) throws Exception {
        try {
            volunteerRevertRepository.save(volunteer);
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            throw new Exception();
        }
    }

    /*Method to retrieve the list of volunteer details from the database*/
    @Override
    public List<Volunteer> getAllVolunteers() throws Exception {
        try {
            return (List<Volunteer>) volunteerRevertRepository.findAll();
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            throw new Exception();
        }
    }

    /*Method to update the volunteer revert details in the database*/
    @Override
    public void volunteerRevertUpdate(VolunteerIncoming volunteerIncoming) throws Exception {
        Volunteer updatedVolunteer = new Volunteer("", 0, "");
        int updatedScore = 0;

        try {
            Optional<Volunteer> optional = volunteerRevertRepository.findById(volunteerIncoming.getEmailId());
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
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            throw new Exception();
        }
    }


    /*Method to determine the score based on the type of action performed by the volunteer*/
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

    /*Method to determine the Level based on the score of the volunteer*/
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



