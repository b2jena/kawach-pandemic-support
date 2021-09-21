package com.stackroute.usermanagementservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Document(collection = "doctors")
public class Doctor {
    @Id
    private String emailId;
    private String fullName;
    private String medId;
    private int phoneNo;
    private String Password;
    private String picUrl;

}
