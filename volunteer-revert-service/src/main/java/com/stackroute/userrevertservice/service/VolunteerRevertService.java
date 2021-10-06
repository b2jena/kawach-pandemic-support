package com.stackroute.userrevertservice.service;

import com.stackroute.userrevertservice.model.Volunteer;
import com.stackroute.userrevertservice.model.VolunteerIncoming;

public interface VolunteerRevertService {
    void volunteerRevertUpdate(VolunteerIncoming volunteerIncoming);

    void saveVolunteer(Volunteer volunteer);
}
