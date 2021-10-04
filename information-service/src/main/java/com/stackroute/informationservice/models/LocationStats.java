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
    private String loc;
    private String confirmedCasesIndian;
    private String confirmedCasesForeign;
    private String discharged;
    private String deaths;
    private String totalConfirmed;

    @Override
    public String toString() {
        return "LocationStats{" +
                "loc='" + loc + '\'' +
                ", confirmedCasesIndian='" + confirmedCasesIndian + '\'' +
                ", confirmedCasesForeign='" + confirmedCasesForeign + '\'' +
                ", discharged='" + discharged + '\'' +
                ", deaths='" + deaths + '\'' +
                ", totalConfirmed='" + totalConfirmed + '\'' +
                '}';
    }
}
