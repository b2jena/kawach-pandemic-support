package com.stackroute.resource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
/*
 * This is a Model class, this contains equipmentId, equipmentName, city, hospital, address, contactPerson, mobileNumber, verificationStatus
 * The Equipment Data will be stored in this format.
 * */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "equipments")
public class Equipments {
    @Transient
    public static final String SEQUENCE_NAME="sequence";
    @Id
    private UUID equipmentId;
    private String equipmentName;
    private String city;
    private String hospital;
    private String address;
    private String contactPerson;
    private String mobileNumber;
    private Boolean verificationStatus;
}
