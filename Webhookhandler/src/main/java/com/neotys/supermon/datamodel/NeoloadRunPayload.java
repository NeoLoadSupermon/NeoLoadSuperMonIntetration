package com.neotys.supermon.datamodel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.neotys.supermon.conf.Constants.NEOLOAD_VENDORNAME;

//vendorName": "NeoLoad",
//    "currentload": 1,
//    "usecaseIdentifier":"FIND-PRODUCTS",
//    "starttimestamp": "2021-03-25T23:00:00.000",
//    "loadDetail": [
//        {
//            "population":"01",
//            "scanerio": "TEST1",
//            "noOfVu": 10
//        }
//    ]
public class NeoloadRunPayload {
    Integer  currentload;
    String usecaseIdentifier;
    String starttimestamp;
    String vendorName;
    List<LoadDetail> loadDetail=new ArrayList<>();

    public Integer getCurrentload() {
        return currentload;
    }

    public void addLoadDetail(LoadDetail loadDetail)
    {
        this.loadDetail.add(loadDetail);
    }
    public void setCurrentload(Integer currentload) {
        this.currentload = currentload;
    }

    public String getUsecaseIdentifier() {
        return usecaseIdentifier;
    }

    public void setUsecaseIdentifier(String usecaseIdentifier) {
        this.usecaseIdentifier = usecaseIdentifier;
    }

    public String getStarttimestamp() {
        return starttimestamp;
    }

    public void setStarttimestamp(String starttimestamp) {
        this.starttimestamp = starttimestamp;
    }

    public List<LoadDetail> getLoadDetail() {
        return loadDetail;
    }

    public void setLoadDetail(List<LoadDetail> loadDetail) {
        this.loadDetail = loadDetail;
    }

    public NeoloadRunPayload(Integer currentload, String usecaseIdentifier, String starttimestamp, List<LoadDetail> loadDetail) {
        this.currentload = currentload;
        this.usecaseIdentifier = usecaseIdentifier;
        this.starttimestamp = starttimestamp;
        this.loadDetail = loadDetail;
        this.vendorName=NEOLOAD_VENDORNAME;
    }

    public NeoloadRunPayload( String usecaseIdentifier, long starttimestamp) {

        this.usecaseIdentifier = usecaseIdentifier;
        Date now = new Date();

        DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        this.starttimestamp= m_ISO8601Local.format(now);
        this.vendorName=NEOLOAD_VENDORNAME;

    }
}
