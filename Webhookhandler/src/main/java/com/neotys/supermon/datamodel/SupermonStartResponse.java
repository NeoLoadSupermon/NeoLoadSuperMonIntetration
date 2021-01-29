package com.neotys.supermon.datamodel;

public class SupermonStartResponse extends SupermonResponse {
    StartData data;
    public SupermonStartResponse(String status, Integer code, StartData data, String errormessage, String errcode, String reportLink) {
        super(status,code,errormessage,errcode, reportLink);
        this.data = data;

    }

    public StartData getData() {
        return data;
    }

    public void setData(StartData data) {
        this.data = data;
    }
}
