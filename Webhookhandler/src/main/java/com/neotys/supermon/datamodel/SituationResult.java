package com.neotys.supermon.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SituationResult {
    SuPerMonEntry entries;
    Integer dataSourceId;
    String databaseName;
    String schemaName;
    String hostUrl;
    String databaseType;

    HashMap<String,Object> data;
    List<MySuperMonEvent> events=new ArrayList<>();

    public SituationResult(HashMap<String,Object> data, Integer dataSourceId, String databaseType,String databaseName, String schemaName, String hostUrl, List<MySuperMonEvent> events) {
        this.data = data;
        this.dataSourceId = dataSourceId;
        this.databaseName = databaseName;
        this.schemaName = schemaName;
        this.databaseType=databaseType;
        this.hostUrl = hostUrl;
        this.events=events;

    }

     public void convertToSuperMonEntry()
    {

        if(data!=null)
            entries=new SuPerMonEntry(data);

    }


    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public List<MySuperMonEvent> getEvents() {
        return events;
    }

    public void setEvents(List<MySuperMonEvent> events) {
        this.events = events;
    }

    public SuPerMonEntry getData() {
        return entries;
    }

    public void setData(SuPerMonEntry data) {
        this.entries= data;
    }

    public Integer getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Integer dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }
}
