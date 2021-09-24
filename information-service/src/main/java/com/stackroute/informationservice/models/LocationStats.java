package com.stackroute.informationservice.models;

public class LocationStats {
    private String loc;
    private String confirmedCasesIndian;
    private String confirmedCasesForeign;
    private String discharged;
    private String deaths;
    private String totalConfirmed;

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getConfirmedCasesIndian() {
        return confirmedCasesIndian;
    }

    public void setConfirmedCasesIndian(String confirmedCasesIndian) {
        this.confirmedCasesIndian = confirmedCasesIndian;
    }

    public String getConfirmedCasesForeign() {
        return confirmedCasesForeign;
    }

    public void setConfirmedCasesForeign(String confirmedCasesForeign) {
        this.confirmedCasesForeign = confirmedCasesForeign;
    }

    public String getDischarged() {
        return discharged;
    }

    public void setDischarged(String discharged) {
        this.discharged = discharged;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(String totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

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
