package com.neotys.supermon.datamodel;

public class SupermonStartResponse extends SupermonResponse {
    Boolean data;

    public SupermonStartResponse(String status,Integer code,Boolean data) {
        super(status,code);
        this.data = data;

    }

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }
}
