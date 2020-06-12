package com.neotys.supermon.datamodel;

public class SupermonResponse {
    //
    // {"status":"SUCCESS",
    // "responseCode":200,
    // "data":
    //    {
    //    "instanceInformation":
    //       {
    //          "usecaseIdentifier":"NeoLoad_Load_test",
    //          "databaseType":"MYSQL",
    //           "instanceName":"172.17.0.1",
    //           "databaseName":"users_database",
    //           "schemaName":null,
    //            "instanceInformationDetails":
    //             {
    //                 "TOTAL_MEMORY_ALLOCATED_IN_MB":0.0,
    //                 "STATEMENTS":9575.0,
    //                 "STATEMENT_LATENCY_IN_S":3.67,
    //                 "FILE_IO_LATENCY_IN_S":1.78,
    //                 "CURRENT_CONNECTIONS":20.0,
    //                 "DATABASE_SIZE_IN_MB":0.0,
    //                 "STATEMENT_AVG_LATENCY_IN_MS":0.38,
    //                 "FILE_IOS":3971.0,
    //                  "TOTAL_CONNECTIONS":1286.0,
    //                   "TABLE_SCANS":208.0,
    //                   "CURRENT_MEMORY_IN_MB":0.0,
    //                  "UNIQUE_USERS":1.0
    //             }
    //        },
    //         "data":true,
    //          "applicationUrl":"http://3.1.221.225:8110/mySuperMon/"
    // },
    // "errorMessage":null,"errorCode":null}"}
    String status;
    Integer responseCode;
    String errorMessage;
    String errorCode;


    public SupermonResponse(String status, Integer responseCode,String errorMessage,String errorCode) {
        this.status = status;
        this.responseCode = responseCode;
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
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
