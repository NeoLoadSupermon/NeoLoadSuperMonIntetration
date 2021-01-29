package com.neotys.supermon.datamodel;


import com.neotys.ascode.api.v3.client.model.CustomMonitor;
import com.neotys.supermon.Logger.NeoLoadLogger;

import java.util.ArrayList;
import java.util.List;

public class SuperMonData extends SupermonResponse{
    SuperMonRunData data;

	//    {
	//        "status": "200 OK",
	//        "responseCode": 200,
	//        "data": {
    //			  "idNum": "12",
	//            "usecaseIdentifier": "LOGIN",
	//            "applicationId": 1,
	//            "applicationName": "mySuperMon",
	//            "applicationIdentifier": "48f85bf4-3ff7-42e3-b56d-e11e7dfae752",
	//            "runSituationResult": [
	//                {
	//                    "dataSourceId": 2,
	//                    "databaseName": "easypay",
	//                    "schemaName": null,
	//                    "hostUrl": "localhost",
	//                    "data": {
	//                        "SUM_ROWS_AFFECTED": 42,
	//                        "SUM_SELECT_RANGE": 0,
	//                        "SUM_ROWS_SENT": null,
	//                        "SUM_SELECT_SCAN": -722,
	//                        "APPLICATION_ID": 1,
	//                        "SUM_NO_GOOD_INDEX_USED": 0,
	//                        "EXEC_TIME_MAX": null,
	//                        "SUM_SORT_SCAN": 166,
	//                        "DATA_SOURCE_ID": 2,
	//                        "SUM_TIMER_WAIT": -11384405281294.0000,
	//                        "SUM_ROWS_EXAMINED": null,
	//                        "SUM_SELECT_FULL_JOIN": 25,
	//                        "COUNT_STAR": -595,
	//                        "SUM_SELECT_FULL_RANGE_JOIN": 0,
	//                        "SUM_SORT_MERGE_PASSES": 0,
	//                        "SUM_SORT_RANGE": 0,
	//                        "SUM_LOCK_TIME": 17677871000000.0000,
	//                        "SUM_SORT_ROWS": 294,
	//                        "SUM_ERRORS": 1,
	//                        "SUM_SELECT_RANGE_CHECK": 0,
	//                        "USECASE_IDENTIFIER": "LOGIN",
	//                        "STARTTIMESTMAP": "2020-12-19T12:35:02.000+0000",
	//                        "SCHEMA_NAME": "easypay",
	//                        "SUM_NO_INDEX_USED": -894
	//                    }
	//                },
	//                {
	//                    "dataSourceId": 3,
	//                    "databaseName": "eduvator",
	//                    "schemaName": null,
	//                    "hostUrl": "localhost",
	//                    "data": {
	//                        "SUM_ROWS_AFFECTED": -42,
	//                        "SUM_SELECT_RANGE": 0,
	//                        "SUM_ROWS_SENT": null,
	//                        "SUM_SELECT_SCAN": 791,
	//                        "APPLICATION_ID": 1,
	//                        "SUM_NO_GOOD_INDEX_USED": 0,
	//                        "EXEC_TIME_MAX": null,
	//                        "SUM_SORT_SCAN": -166,
	//                        "DATA_SOURCE_ID": 3,
	//                        "SUM_TIMER_WAIT": 11896424723520.0000,
	//                        "SUM_ROWS_EXAMINED": null,
	//                        "SUM_SELECT_FULL_JOIN": -25,
	//                        "COUNT_STAR": 664,
	//                        "SUM_SELECT_FULL_RANGE_JOIN": 0,
	//                        "SUM_SORT_MERGE_PASSES": 0,
	//                        "SUM_SORT_RANGE": 0,
	//                        "SUM_LOCK_TIME": -17671393000000.0000,
	//                        "SUM_SORT_ROWS": -294,
	//                        "SUM_ERRORS": -1,
	//                        "SUM_SELECT_RANGE_CHECK": 0,
	//                        "USECASE_IDENTIFIER": "LOGIN",
	//                        "STARTTIMESTMAP": "2020-12-19T12:35:03.000+0000",
	//                        "SCHEMA_NAME": "eduvator",
	//                        "SUM_NO_INDEX_USED": 963
	//                    }
	//                }
	//            ]
	//        },
	//        "errorMessage": null,
	//        "errorCode": null
	//    }




    public SuperMonData(String status, Integer responseCode, SuperMonRunData entries, String errmessage, String errorcode, String reportLink) {
        super(status, responseCode,errmessage,errorcode, reportLink);
        this.data=entries;
    }


    public SuperMonRunData getData() {
        return data;
    }

    public void setData(SuperMonRunData data) {
        this.data = data;
    }

    public List<CustomMonitor> toCustomMonitor(NeoLoadLogger logger) {
        List<CustomMonitor> customMonitors=new ArrayList<>();
        logger.debug("Parsing the entries");

        data.getRunSituationResult().stream().forEach(suPerMonEntry -> {
            suPerMonEntry.convertToSuperMonEntry();
            logger.debug("parsing the data of database scheme:  "+suPerMonEntry.getSchemaName());
            suPerMonEntry.getData().toCustomMonitor(customMonitors,suPerMonEntry.getDatabaseType(),suPerMonEntry.getDatabaseName(),logger);
        });

        return customMonitors;
    }
   
}
