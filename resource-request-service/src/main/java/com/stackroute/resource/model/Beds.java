package com.stackroute.resource.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "beds")
public class Beds {
    @Transient
    public static final String SEQUENCE_NAME="sequence";
//    @Id
//    private UUID bedId;
    private String bedType;
    private String city;
    private String address;
    private String contactPerson;
    private String mobileNumber;
    private Boolean verificationStatus;
}
