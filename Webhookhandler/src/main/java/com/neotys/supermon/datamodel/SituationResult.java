package com.neotys.supermon.datamodel;

import java.util.HashMap;

public class SituationResult {
    SuPerMonEntry entries;
    Integer dataSourceId;
    String databaseName;
    String schemaName;
    String hostUrl;
    String databaseType;

    HashMap<String,Object> data;

    public SituationResult(HashMap<String,Object> data, Integer dataSourceId, String databaseType,String databaseName, String schemaName, String hostUrl) {
        this.data = data;
        this.dataSourceId = dataSourceId;
        this.databaseName = databaseName;
        this.schemaName = schemaName;
        this.databaseType=databaseType;
        this.hostUrl = hostUrl;
    }

     public void convertToSuperMonEntry()
    {

        if(data!=null)
            entries=new SuPerMonEntry(data);

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
