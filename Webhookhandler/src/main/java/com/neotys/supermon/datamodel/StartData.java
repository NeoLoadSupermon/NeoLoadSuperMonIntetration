package com.neotys.supermon.datamodel;

public class StartData {
    private InstanceInformation instanceInformation;
    boolean data;
    private String applicationUrl;

    public StartData(InstanceInformation instanceInformation, boolean data, String applicationUrl) {
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

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }
}
