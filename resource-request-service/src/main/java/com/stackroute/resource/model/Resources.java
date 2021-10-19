package com.stackroute.resource.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/*
 * This is a Model class, this contains Id, medicineName, city, pharmacy, address, contactPerson, mobileNumber, verificationStatus
 * The Resource Data will be stored in this format.
 * */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "resources")
public class Resources {
    @Transient
    public static final String SEQUENCE_NAME="sequence";
    @Id
    private UUID id;
    private String medicineName;
    private String city;
    private String pharmacy;
    private String address;
    private String contactPerson;
    private String mobileNumber;
    private Boolean verificationStatus;
}
