package com.neotys.supermon.datamodel;

import java.util.HashMap;

public class InstanceInformation {

    //instanceInformation":
    //    //       {
    //    //          "usecaseIdentifier":"NeoLoad_Load_test",
    //    //          "databaseType":"MYSQL",
    //    //           "instanceName":"172.17.0.1",
    //    //           "databaseName":"users_database",
    //    //           "schemaName":null,
    //    //            "instanceInformationDetails":
    //    //             {
    //    //                 "TOTAL_MEMORY_ALLOCATED_IN_MB":0.0,
    //    //                 "STATEMENTS":9575.0,
    //    //                 "STATEMENT_LATENCY_IN_S":3.67,
    //    //                 "FILE_IO_LATENCY_IN_S":1.78,
    //    //                 "CURRENT_CONNECTIONS":20.0,
    //    //                 "DATABASE_SIZE_IN_MB":0.0,
    //    //                 "STATEMENT_AVG_LATENCY_IN_MS":0.38,
    //    //                 "FILE_IOS":3971.0,
    //    //                  "TOTAL_CONNECTIONS":1286.0,
    //    //                   "TABLE_SCANS":208.0,
    //    //                   "CURRENT_MEMORY_IN_MB":0.0,
    //    //                  "UNIQUE_USERS":1.0
    //    //             }
    //    //        },
    private String usecaseIdentifier;
    private String databaseType;
    private String databaseName;
    private String schemaName;
    private HashMap<String,Double> instanceInformationDetails;

    public InstanceInformation(String usecaseIdentifier, String databaseType, String databaseName, String schemaName, HashMap<String, Double> instanceInformationDetails) {
        this.usecaseIdentifier = usecaseIdentifier;
        this.databaseType = databaseType;
        this.databaseName = databaseName;
        this.schemaName = schemaName;
        this.instanceInformationDetails = instanceInformationDetails;
    }

    public String getUsecaseIdentifier() {
        return usecaseIdentifier;
    }

    public void setUsecaseIdentifier(String usecaseIdentifier) {
        this.usecaseIdentifier = usecaseIdentifier;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
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

    public HashMap<String, Double> getInstanceInformationDetails() {
        return instanceInformationDetails;
    }

    public void setInstanceInformationDetails(HashMap<String, Double> instanceInformationDetails) {
        this.instanceInformationDetails = instanceInformationDetails;
    }
}


