package com.neotys.supermon.datamodel;


import com.neotys.ascode.api.v3.client.model.CustomMonitor;
import com.neotys.ascode.api.v3.client.model.CustomMonitorValues;
import com.neotys.ascode.api.v3.client.model.CustomMonitorValuesInner;
import com.neotys.supermon.Logger.NeoLoadLogger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import static com.neotys.supermon.conf.Constants.DATABASE;
import static com.neotys.supermon.conf.Constants.SUPERMON;

public class SuPerMonEntry {

    //{
    //	//                    "dataSourceId": 2,
    //	//                    "databaseName": "easypay",
    //	//                    "schemaName": null,
    //	//                    "hostUrl": "localhost",
    //	//                    "data": {
    //	//                        "SUM_ROWS_AFFECTED": 42,
    //	//                        "SUM_SELECT_RANGE": 0,
    //	//                        "SUM_ROWS_SENT": null,
    //	//                        "SUM_SELECT_SCAN": -722,
    //	//                        "APPLICATION_ID": 1,
    //	//                        "SUM_NO_GOOD_INDEX_USED": 0,
    //	//                        "EXEC_TIME_MAX": null,
    //	//                        "SUM_SORT_SCAN": 166,
    //	//                        "DATA_SOURCE_ID": 2,
    //	//                        "SUM_TIMER_WAIT": -11384405281294.0000,
    //	//                        "SUM_ROWS_EXAMINED": null,
    //	//                        "SUM_SELECT_FULL_JOIN": 25,
    //	//                        "COUNT_STAR": -595,
    //	//                        "SUM_SELECT_FULL_RANGE_JOIN": 0,
    //	//                        "SUM_SORT_MERGE_PASSES": 0,
    //	//                        "SUM_SORT_RANGE": 0,
    //	//                        "SUM_LOCK_TIME": 17677871000000.0000,
    //	//                        "SUM_SORT_ROWS": 294,
    //	//                        "SUM_ERRORS": 1,
    //	//                        "SUM_SELECT_RANGE_CHECK": 0,
    //	//                        "USECASE_IDENTIFIER": "LOGIN",
    //	//                        "STARTTIMESTMAP": "2020-12-19T12:35:02.000+0000",
    //	//                        "SCHEMA_NAME": "easypay",
    //	//                        "SUM_NO_INDEX_USED": -894
    //	//                    }
    String STARTTIMESTMAP;
    Map<String,String> MapwString;
    Map<String,Double>  MapWDouble;
    String USECASE_IDENTIFIER;
    Integer ID_NUM;
    Integer ID;

    public SuPerMonEntry(String STARTTIMESTMAP, String uscase, Integer id_num, Integer id, HashMap<String, String> mapwString, HashMap<String, Double> mapWDouble) {
        this.STARTTIMESTMAP = STARTTIMESTMAP;
        this.MapwString = mapwString;
        this.MapWDouble = mapWDouble;
        this.USECASE_IDENTIFIER=uscase;
        this.ID_NUM=id_num;
        this.ID=id;

    }
    public SuPerMonEntry(Map<String,Object> map)
    {
        MapwString = new HashMap<>();
        MapWDouble=new HashMap<>();
        if(map.containsKey("STARTTIMESTAMP"))
        {
            this.STARTTIMESTMAP=(String)map.get("STARTTIMESTAMP");
        }
        else
            this.STARTTIMESTMAP=null;

        if(map.containsKey("USECASE_IDENTIFIER"))
        {
            this.USECASE_IDENTIFIER=(String)map.get("USECASE_IDENTIFIER");
        }
        else
            this.USECASE_IDENTIFIER=null;

        if(map.containsKey("ID_NUM"))
        {
            this.ID_NUM=(Integer)map.get("ID_NUM");
        }
        else
            this.ID_NUM=null;

        if(map.containsKey("ID"))
        {
            this.ID=(Integer)map.get("ID");
        }
        else
            this.ID=null;

        map.forEach((s, o) -> {
            if(o!=null) {
                if (o instanceof String) {
                    if(!s.equalsIgnoreCase("USECASE_IDENTIFIER")&&!s.equalsIgnoreCase("ID")&&!s.equalsIgnoreCase("ID_NUM")&&!s.equalsIgnoreCase("STARTTIMESTMAP"))
                         this.MapwString.put(s, (String) o);
                } else {
                    if (o instanceof Double) {
                        this.MapWDouble.put(s, (Double) o);
                    }
                }
            }
        });

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
        // TODO: Its a dirty fix, but will do the correct inputs from mySuperMon @Pravin please fix this.
        Date result= STARTTIMESTMAP ==null ? new Date() : m_ISO8601Local.parse(STARTTIMESTMAP);
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

    public void setMapwString(HashMap<String, String> mapwString) {
        MapwString = mapwString;
    }

    public Map<String, Double> getMapWDouble() {
        return MapWDouble;
    }

    public void setMapWDouble(HashMap<String, Double> mapWDouble) {
        MapWDouble = mapWDouble;
    }

    public void toCustomMonitor(List<CustomMonitor> customMonitors, String databaseType, String databaseName, NeoLoadLogger logger)
    {

        List<String> path = new ArrayList<>();
        path.add(DATABASE);
        path.add(SUPERMON);
        path.add(databaseType);
        path.add(databaseName);
        logger.debug("Path-> "+String.join("/",path));
        if(MapWDouble!=null) {
            MapWDouble.forEach((s, aDouble) -> {
                if (aDouble != null) {
                    List<String> metricPath = new ArrayList<>();
                    metricPath.addAll(path);
                    CustomMonitor monitor = new CustomMonitor();
                    logger.debug("Path-> "+String.join("/",metricPath));
                    monitor.setPath(metricPath);
                    monitor.setUnit(null);
                    monitor.setName(s.toLowerCase());
                    CustomMonitorValues valuesInners = new CustomMonitorValues();
                    // valuesInners.s
                    CustomMonitorValuesInner customMonitorValuesInner = new CustomMonitorValuesInner();
                    try {
                        customMonitorValuesInner.setTimestamp(convertDate() / 1000);
                        logger.debug("date of metrics "+ String.valueOf(convertDate()/1000));
                    } catch (ParseException e) {
                        customMonitorValuesInner.setTimestamp(Instant.now().getEpochSecond());
                    }

                    customMonitorValuesInner.setValue((float) aDouble.doubleValue());
                    logger.debug("Value -> "+String.valueOf((float) aDouble.doubleValue()));
                    valuesInners.add(customMonitorValuesInner);
                    monitor.setValues(valuesInners);
                    customMonitors.add(monitor);
                }
            });
        }
        else
            logger.debug("Map double is empty");


    }
}
