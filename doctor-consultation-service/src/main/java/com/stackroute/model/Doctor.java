package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "doctors")
public class Doctor implements Serializable {
    @Id
    private String emailId;
    private String fullName;
    private String specialization;
    private static final long serialVersionUID = 200856043767861933L;

}
