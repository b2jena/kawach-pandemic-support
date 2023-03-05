package com.stackroute.resource.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "SOSRequest")
public class MedicalSosRequest {
    @Transient
    public static final String SEQUENCE_NAME = "sequence";
    public String city;
    public ArrayList<Requirement> requirement;
    @Id
    private UUID requestId;
    private String patientName;
    private String gender;
    private String phoneNo;
    private String email;
    private String hospitalised;
    private String requestStatus;
    private String formStatus;
}
