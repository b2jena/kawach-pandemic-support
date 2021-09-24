package com.stackroute.resource.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "resources")
public class Resources {
    @Id
    private int id;
    private String medicineName;
    private String city;
    private String pharmacy;
    private String address;
    private String contactPerson;
    private String mobileNumber;
}
