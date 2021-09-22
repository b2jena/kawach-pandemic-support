package com.stackroute.usermanagementservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "volunteers")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Volunteer {
    @Id
    private String emailId;
    private String fullName;
    private int phoneNo;
    private String password;
}
