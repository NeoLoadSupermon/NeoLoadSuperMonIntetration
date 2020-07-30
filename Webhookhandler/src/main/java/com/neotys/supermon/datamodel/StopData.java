package com.neotys.supermon.datamodel;

import java.util.List;

public class StopData {
    private InstanceInformation instanceInformation;
    List<TestData> data;
    private String applicationUrl;

     //"data":
        //   "errorMessage":null,
        //   "errorCode":null
        //}"\"}"


    public StopData(InstanceInformation instanceInformation, List<TestData> data, String applicationUrl) {
        this.instanceInformation = instanceInformation;
        this.data = data;
        this.applicationUrl = applicationUrl;
    }

    public InstanceInformation getInstanceInformation() {
        return instanceInformation;
    }

    public void setInstanceInformation(InstanceInformation instanceInformation) {
        this.instanceInformation = instanceInformation;
    }

    public List<TestData> getData() {
        return data;
    }

    public void setData(List<TestData> data) {
        this.data = data;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }
}
