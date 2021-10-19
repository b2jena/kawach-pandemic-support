package com.stackroute.informationservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
 * This is a Model class, this contains Location, Confirmed cases for indian, Confirmed cases for foreighn, no of people discharged, deaths and total confirmed
 * the api Data will be stored in this format.
 * */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationStats {
    private String strLoc;
    private String strConfirmedCasesIndian;
    private String strConfirmedCasesForeign;
    private String strDischarged;
    private String strDeaths;
    private String strTotalConfirmed;

    /*To convert to string for displaying purposes*/
    @Override
    public String toString() {
        return "LocationStats{" +
                "loc='" + strLoc + '\'' +
                ", confirmedCasesIndian='" + strConfirmedCasesIndian + '\'' +
                ", confirmedCasesForeign='" + strConfirmedCasesForeign + '\'' +
                ", discharged='" + strDischarged + '\'' +
                ", deaths='" + strDeaths + '\'' +
                ", totalConfirmed='" + strTotalConfirmed + '\'' +
                '}';
    }
}
