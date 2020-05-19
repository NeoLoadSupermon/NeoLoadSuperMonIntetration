package com.neotys.supermon.datamodel;

public class SupermonResponse {
    //{
    //    "status": "SUCCESS",
    //    "responseCode": 200,
    //    "data": true
    //}
    String status;
    Integer responseCode;

    public SupermonResponse(String status, Integer responseCode) {
        this.status = status;
        this.responseCode = responseCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }
}
