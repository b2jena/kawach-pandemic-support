package com.stackroute.userrevertservice.service;

import com.stackroute.userrevertservice.model.Volunteer;
import com.stackroute.userrevertservice.model.VolunteerIncoming;

import java.util.List;

/*Interface containing the methods that needs to be implemented in the implementation class*/
public interface VolunteerRevertService {
    void volunteerRevertUpdate(VolunteerIncoming volunteerIncoming) throws Exception;

    void saveVolunteer(Volunteer volunteer) throws Exception;

    List<Volunteer> getAllVolunteers() throws Exception;
}
