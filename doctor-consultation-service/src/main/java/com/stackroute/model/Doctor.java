package com.stackroute.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Doctor {
    @Id
    private String email;
    private String firstname;
    private String lastname;
    private int status;

    public Doctor() {
        super();
    }

    public Doctor(String email, String firstname, String lastname, int status) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
