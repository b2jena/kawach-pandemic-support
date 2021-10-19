package com.stackroute.userrevertservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*Volunteer bean class containing all the Volunteer revert parameters*/
@Document(collection = "volunteerRevert")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Volunteer {

    @Id
    private String volunteerEmailId;
    private int score;
    private String level;
}
