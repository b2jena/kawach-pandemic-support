package com.stackroute.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

//Patient entity
@Entity
public class Patient {
    @Id
    private String email;

    //Empty constructor
    public Patient() {

    }
    //Parameterized Constructors
    public Patient(String email) {
        this.email = email;
    }
    //getter methods
    public String getEmail() {
        return email;
    }
    //setter methods
    public void setEmail(String email) {
        this.email = email;
    }
}
