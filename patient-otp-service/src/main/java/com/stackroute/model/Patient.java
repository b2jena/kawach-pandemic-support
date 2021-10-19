package com.stackroute.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
/*
 * This is a Model class, this contains Email-Id
 * */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {
    @Id
    private String strEmail;
}
