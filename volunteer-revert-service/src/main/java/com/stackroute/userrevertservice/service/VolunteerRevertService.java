package com.stackroute.userrevertservice.service;

import com.stackroute.userrevertservice.model.Volunteer;
import com.stackroute.userrevertservice.model.VolunteerIncoming;

import java.util.List;

public interface VolunteerRevertService {
    void volunteerRevertUpdate(VolunteerIncoming volunteerIncoming);

    void saveVolunteer(Volunteer volunteer);

    List<Volunteer> getAllVolunteers();
}
