package com.neotys.supermon.datamodel;

public class LoadDetail {


//            "population":"01",
//            "scanerio": "TEST1",
//            "noOfVu": 10
//        }

    String population;
    String scanerio;
    Integer noOfVu;

    public LoadDetail(String population, String scanerio, Integer noOfVu) {
        this.population = population;
        this.scanerio = scanerio;
        this.noOfVu = noOfVu;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getScanerio() {
        return scanerio;
    }

    public void setScanerio(String scanerio) {
        this.scanerio = scanerio;
    }

    public Integer getNoOfVu() {
        return noOfVu;
    }

    public void setNoOfVu(Integer noOfVu) {
        this.noOfVu = noOfVu;
    }
}
