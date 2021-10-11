package com.stackroute.usermanagementservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*Doctor bean class containing all the registration parameters*/

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "doctors")
public class Doctor {
    @Id
    private String emailId;
    private String fullName;
    private String medicalRegistrationId;
    private String code;
    private long phoneNumber;
    private String password;
    private String specialization;
    private byte[] imageByte;

    private String imageName;

    private String type;


}
