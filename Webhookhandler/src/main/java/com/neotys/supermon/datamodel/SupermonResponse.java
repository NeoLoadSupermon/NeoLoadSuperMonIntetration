package com.neotys.supermon.datamodel;

public class SupermonResponse {
	//	{
	//	    "status": "SUCCESS",
	//	    "responseCode": 200,
	//	    "data": {
	//	        "idNum": 22,
	//	        "usecaseIdentifier": "LOGIN",
	//	        "applicationId": 1,
	//	        "applicationName": "mySuperMon",
	//	        "applicationIdentifier": "48f85bf4-3ff7-42e3-b56d-e11e7dfae752",
	//	        "dataSourceDTO": [
	//	            {
	//	                "dataSourceId": 2,
	//	                "databaseName": "easypay",
	//	                "schemaName": "easypay",
	//	                "hostUrl": "localhost",
	//	                "instanceInformation": {
	//	                    "STATEMENTS": 221160.0,
	//	                    "STATEMENT_LATENCY_IN_S": 2153.86,
	//	                    "FILE_IO_LATENCY_IN_S": 1540.15,
	//	                    "CURRENT_CONNECTIONS": 14.0,
	//	                    "DATABASE_SIZE_IN_MB": 0.1,
	//	                    "STATEMENT_AVG_LATENCY_IN_MS": 9.74,
	//	                    "APPLICATION_ID": 1.0,
	//	                    "FILE_IOS": 305867.0,
	//	                    "TABLE_SCANS": 35670.0,
	//	                    "DATA_SOURCE_ID": 2.0,
	//	                    "USECASE_IDENTIFIER": 0.0,
	//	                    "UNIQUE_USERS": 1.0
	//	                },
	//	                "dataList": null
	//	            },
	//	            {
	//	                "dataSourceId": 3,
	//	                "databaseName": "eduvator",
	//	                "schemaName": "eduvator",
	//	                "hostUrl": "localhost",
	//	                "instanceInformation": {
	//	                    "STATEMENTS": 221160.0,
	//	                    "STATEMENT_LATENCY_IN_S": 2153.86,
	//	                    "FILE_IO_LATENCY_IN_S": 1540.15,
	//	                    "CURRENT_CONNECTIONS": 14.0,
	//	                    "DATABASE_SIZE_IN_MB": 0.1,
	//	                    "STATEMENT_AVG_LATENCY_IN_MS": 9.74,
	//	                    "APPLICATION_ID": 1.0,
	//	                    "FILE_IOS": 305867.0,
	//	                    "TABLE_SCANS": 35670.0,
	//	                    "DATA_SOURCE_ID": 2.0,
	//	                    "USECASE_IDENTIFIER": 0.0,
	//	                    "UNIQUE_USERS": 1.0
	//	                },
	//	                "dataList": null
	//	            }
	//	        ]
	//	    },
	//	    "errorMessage": null,
	//	    "errorCode": null
	//	}
    String status;
    Integer responseCode;
    String errorMessage;
    String errorCode;
    String reportLink;

    public SupermonResponse(String status, Integer responseCode,String errorMessage,String errorCode, String reportLink) {
        this.status = status;
        this.responseCode = responseCode;
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
        this.reportLink = reportLink;
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


	public String getReportLink() {
		return reportLink;
	}


	public void setReportLink(String reportLink) {
		this.reportLink = reportLink;
	}
    
}
