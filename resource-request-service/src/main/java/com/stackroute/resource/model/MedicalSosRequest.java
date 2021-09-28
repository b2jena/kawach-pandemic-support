package com.stackroute.resource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "SOSRequest")
public class MedicalSosRequest {
    @Transient
    public static final String SEQUENCE_NAME="sequence";
    @Id
    private UUID requestId;  //__
    private String patientName;
    private String gender;
    private String phoneNo;
    private String email;
    private String hospitalised;
    private String city;
//    private ArrayList<Requirement> requirement;
    private String requestStatus;
    private String uploadPrescription;
}
