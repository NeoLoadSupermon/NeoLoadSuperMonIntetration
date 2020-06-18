package com.neotys.supermon.datamodel;

import com.neotys.ascode.swagger.client.model.CustomMonitor;
import com.neotys.ascode.swagger.client.model.CustomMonitorValues;
import com.neotys.ascode.swagger.client.model.CustomMonitorValuesInner;
import com.neotys.supermon.Logger.NeoLoadLogger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import static com.neotys.supermon.conf.Constants.DATABASE;
import static com.neotys.supermon.conf.Constants.SUPERMON;

public class SuPerMonEntry {
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
        if(map.containsKey("STARTTIMESTMAP"))
        {
            this.STARTTIMESTMAP=(String)map.get("STARTTIMESTMAP");
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

    public void setMapwString(HashMap<String, String> mapwString) {
        MapwString = mapwString;
    }

    public Map<String, Double> getMapWDouble() {
        return MapWDouble;
    }

    public void setMapWDouble(HashMap<String, Double> mapWDouble) {
        MapWDouble = mapWDouble;
    }

    public void toCustomMonitor(List<CustomMonitor> customMonitors, String databaseType, String databaseName,  NeoLoadLogger logger)
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
