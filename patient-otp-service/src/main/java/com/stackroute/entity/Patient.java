package com.stackroute.entity;

import javax.persistence.*;

@Entity
@Table(name="cat")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientid;
    private String email;

    public Patient() {
    }

    public Patient(int patientid, String email) {
        this.patientid = patientid;
        this.email = email;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
