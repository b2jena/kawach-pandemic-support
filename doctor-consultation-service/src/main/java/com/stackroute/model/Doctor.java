package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@RedisHash("Doctor")
public class Doctor implements Serializable {
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String status;
    private String specialization;
}
