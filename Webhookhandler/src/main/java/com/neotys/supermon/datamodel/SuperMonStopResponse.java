package com.neotys.supermon.datamodel;

public class SuperMonStopResponse extends SupermonResponse {

    StartData data;

    public SuperMonStopResponse(String status, Integer responseCode, StartData url, String errormessage, String errorcode) {
        super(status, responseCode,errormessage,errorcode);
        this.data=url;
    }

    public StartData getData() {
        return data;
    }

    public void setData(StartData data) {
        this.data = data;
    }
}
