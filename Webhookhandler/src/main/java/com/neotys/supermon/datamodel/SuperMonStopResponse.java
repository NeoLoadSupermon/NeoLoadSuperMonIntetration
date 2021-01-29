package com.neotys.supermon.datamodel;

public class SuperMonStopResponse extends SupermonResponse {
    //#TODO to store in events while waiting for better solution
	//	{
	//	    "status": "SUCCESS",
	//	    "responseCode": 200,
	//	    "data": {
	//	            "applicationId": 1,
	//	            "applicationName": "mySuperMon",
	//	            "applicationIdentifier": "48f85bf4-3ff7-42e3-b56d-e11e7dfae752",
	//	            "idNum": 14,
	//	            "usecaseIdentifier": "LOGIN",
	//	            "startTimestamp": "2020-12-19T12:37:32.000+0000",
	//	            "dataSourceList": [
	//	                {
	//	                    "dataSourceId": 2,
	//	                    "databaseName": "easypay",
	//	                    "schemaName": null,
	//	                    "hostUrl": "easypay",
	//	                    "instanceInformation": {
	//	                        "STATEMENTS": 204061.0,
	//	                        "STATEMENT_LATENCY_IN_S": 2084.03,
	//	                        "FILE_IO_LATENCY_IN_S": 1529.64,
	//	                        "CURRENT_CONNECTIONS": 14.0,
	//	                        "DATABASE_SIZE_IN_MB": 0.1,
	//	                        "STATEMENT_AVG_LATENCY_IN_MS": 10.21,
	//	                        "APPLICATION_ID": 1.0,
	//	                        "FILE_IOS": 302414.0,
	//	                        "TABLE_SCANS": 34148.0,
	//	                        "DATA_SOURCE_ID": 2.0,
	//	                        "USECASE_IDENTIFIER": 0.0,
	//	                        "UNIQUE_USERS": 1.0
	//	                    },
	//	                    "dataList": [
	//	                        {
	//	                            "fieldName": "avgTimerWait",
	//	                            "newValue": 19.5723,
	//	                            "oldValue": 19.5723,
	//	                            "deviation": 0.0
	//	                        },
	//	                        {
	//	                            "fieldName": "execCount",
	//	                            "newValue": 1241.0000,
	//	                            "oldValue": 1241.0000,
	//	                            "deviation": 0.0
	//	                        },
	//	                        {
	//	                            "fieldName": "execTimeAvgMS",
	//	                            "newValue": 19.5723,
	//	                            "oldValue": 19.5723,
	//	                            "deviation": 0.0
	//	                        },
	//	                        {
	//	                            "fieldName": "execTimeMax",
	//	                            "newValue": 21.0014,
	//	                            "oldValue": 21.0014,
	//	                            "deviation": 0.0
	//	                        }
	//	                    ]
	//	                },
	//	                {
	//	                    "dataSourceId": 3,
	//	                    "databaseName": "eduvator",
	//	                    "schemaName": null,
	//	                    "hostUrl": "eduvator",
	//	                    "instanceInformation": {
	//	                        "STATEMENTS": 204061.0,
	//	                        "STATEMENT_LATENCY_IN_S": 2084.03,
	//	                        "FILE_IO_LATENCY_IN_S": 1529.64,
	//	                        "CURRENT_CONNECTIONS": 14.0,
	//	                        "DATABASE_SIZE_IN_MB": 0.1,
	//	                        "STATEMENT_AVG_LATENCY_IN_MS": 10.21,
	//	                        "APPLICATION_ID": 1.0,
	//	                        "FILE_IOS": 302414.0,
	//	                        "TABLE_SCANS": 34148.0,
	//	                        "DATA_SOURCE_ID": 2.0,
	//	                        "USECASE_IDENTIFIER": 0.0,
	//	                        "UNIQUE_USERS": 1.0
	//	                    },
	//	                    "dataList": [
	//	                        {
	//	                            "fieldName": "avgTimerWait",
	//	                            "newValue": 0.5171,
	//	                            "oldValue": 19.5723,
	//	                            "deviation": 97.36
	//	                        },
	//	                        {
	//	                            "fieldName": "execCount",
	//	                            "newValue": 1363.0000,
	//	                            "oldValue": 1241.0000,
	//	                            "deviation": -9.83
	//	                        },
	//	                        {
	//	                            "fieldName": "execTimeAvgMS",
	//	                            "newValue": 0.5171,
	//	                            "oldValue": 19.5723,
	//	                            "deviation": 97.36
	//	                        },
	//	                        {
	//	                            "fieldName": "execTimeMax",
	//	                            "newValue": 5.3596,
	//	                            "oldValue": 21.0014,
	//	                            "deviation": 74.48
	//	                        }
	//	                    ]
	//	                }
	//	            ], 
	//	        "applicationUrl": "http://localhost:8081/"
	//	    },
	//	    "errorMessage": null,
	//	    "errorCode": null
	//	}
    StopData data;

    public SuperMonStopResponse(String status, Integer responseCode, StopData url, String errormessage, String errorcode, String reportLink) {
        super(status, responseCode,errormessage,errorcode, reportLink);
        this.data=url;
    }

    public StopData getData() {
        return data;
    }

    public void setData(StopData data) {
        this.data = data;
    }
}
