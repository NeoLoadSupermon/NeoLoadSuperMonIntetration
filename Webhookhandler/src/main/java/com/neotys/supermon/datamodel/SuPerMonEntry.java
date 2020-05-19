package com.neotys.supermon.datamodel;

import com.neotys.ascode.swagger.client.model.CustomMonitor;
import com.neotys.ascode.swagger.client.model.CustomMonitorValues;
import com.neotys.ascode.swagger.client.model.CustomMonitorValuesInner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static com.neotys.supermon.conf.Constants.DATABASE;
import static com.neotys.supermon.conf.Constants.SUPERMON;

public class SuPerMonEntry {
    String STARTTIMESTMAP;
    Map<String,String> MapwString;
    Map<String,Double>  MapWDouble;
    String USECASE_IDENTIFIER;
    Integer ID_NUM;
    Integer ID;

    public SuPerMonEntry(String STARTTIMESTMAP,String uscase,Integer id_num,Integer id,  Map<String, String> mapwString, Map<String, Double> mapWDouble) {
        this.STARTTIMESTMAP = STARTTIMESTMAP;
        this.MapwString = mapwString;
        this.MapWDouble = mapWDouble;
        this.USECASE_IDENTIFIER=uscase;
        this.ID_NUM=id_num;
        this.ID=id;

    }

    public String getUSECASE_IDENTIFIER() {
        return USECASE_IDENTIFIER;
    }

    public void setUSECASE_IDENTIFIER(String USECASE_IDENTIFIER) {
        this.USECASE_IDENTIFIER = USECASE_IDENTIFIER;
    }

    public Integer getID_NUM() {
        return ID_NUM;
    }

    public void setID_NUM(Integer ID_NUM) {
        this.ID_NUM = ID_NUM;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    private long convertDate() throws ParseException {

        DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date result=m_ISO8601Local.parse(STARTTIMESTMAP);
        return result.getTime();
    }
    public String getSTARTTIMESTMAP() {
        return STARTTIMESTMAP;
    }

    public void setSTARTTIMESTMAP(String STARTTIMESTMAP) {
        this.STARTTIMESTMAP = STARTTIMESTMAP;
    }

    public Map<String, String> getMapwString() {
        return MapwString;
    }

    public void setMapwString(Map<String, String> mapwString) {
        MapwString = mapwString;
    }

    public Map<String, Double> getMapWDouble() {
        return MapWDouble;
    }

    public void setMapWDouble(Map<String, Double> mapWDouble) {
        MapWDouble = mapWDouble;
    }

    public void toCustomMonitor(List<CustomMonitor> customMonitors,String databaseType,String databaseName)
    {

        List<String> path = new ArrayList<>();
        path.add(DATABASE);
        path.add(SUPERMON);
        path.add(databaseType);
        path.add(databaseName);
        MapWDouble.forEach((s, aDouble) -> {
            List<String> metricPath=new ArrayList<>();
            metricPath.addAll(path);
            metricPath.add(s);
            CustomMonitor monitor=new CustomMonitor();
            monitor.setPath(metricPath);
            monitor.setUnit(null);
            monitor.setName(s);
            CustomMonitorValues valuesInners=new CustomMonitorValues();
            // valuesInners.s
            CustomMonitorValuesInner customMonitorValuesInner=new CustomMonitorValuesInner();
            try {
                customMonitorValuesInner.setTimestamp(convertDate()/1000);
            } catch (ParseException e) {
                customMonitorValuesInner.setTimestamp(Instant.now().getEpochSecond());
            }

            customMonitorValuesInner.setValue((float)aDouble.doubleValue());
            valuesInners.add(customMonitorValuesInner);
            monitor.setValues(valuesInners);
        });


    }
}
