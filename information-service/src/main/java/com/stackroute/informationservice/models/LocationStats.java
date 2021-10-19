package com.stackroute.informationservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
