package com.stackroute.userrevertservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*Volunteer Incoming bean class containing the Volunteer action performed parameters*/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VolunteerIncoming {
    private String emailId;
    private String type;
}
