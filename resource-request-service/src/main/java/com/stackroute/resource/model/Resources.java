package com.stackroute.resource.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.GeneratedValue;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "resources")
public class Resources {
    @Id
    @GeneratedValue
    private int id;
    private String medicineName;
    private String city;
    private String pharmacy;
    private String address;
    private String contactPerson;
    private String mobileNumber;
}
