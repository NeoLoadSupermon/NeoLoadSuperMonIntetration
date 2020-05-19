package com.neotys.supermon.datamodel;

public class SuperMonStopResponse extends SupermonResponse {

    String data;

    public SuperMonStopResponse(String status, Integer responseCode,String url) {
        super(status, responseCode);
        this.data=url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
