package com.neotys.supermon.datamodel;

public class DatabaseEntity {

    String databasename;
    String databaseType;
    String schemaName;
    String hostUrl;


    public DatabaseEntity(String databasename, String databaseType,String schemaName, String hostUrl) {
        this.databasename = databasename;
        this.databaseType = databaseType;
        this.schemaName=schemaName;
        this.hostUrl=hostUrl;
    }

    public String getDatabasename() {
        return databasename;
    }

    public void setDatabasename(String databasename) {
        this.databasename = databasename;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
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
}
