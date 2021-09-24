package com.stackroute.entity;

import javax.persistence.*;

@Entity
@Table(name="Patient")
public class Patient {
    @Id

    private String email;

    public Patient() {
    }

    public Patient(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
