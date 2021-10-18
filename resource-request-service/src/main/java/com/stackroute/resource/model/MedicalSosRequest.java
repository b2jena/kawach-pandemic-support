package com.stackroute.resource.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

/*
 * This is a Model class, this contains requestId, Patient Name, Gender, PhoneNo., EmailId, Hospitalized Status, Requirement Array, Request Status, Form Status ,
 * the SOS Request Data will be stored in this format.
 * */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "collSOSRequest")
public class MedicalSosRequest {
    @Transient
    public static final String SEQUENCE_NAME="sequence";
    @Id
    private UUID requestId;
    private String strPatientName;
    private String strGender;
    private String strPhoneNo;
    private String strEmail;
    private String strHospitalised;
    public String strCity;
    public ArrayList<Requirement> arrRequirement;
    private String strRequestStatus;
    private String strFormStatus;
}
