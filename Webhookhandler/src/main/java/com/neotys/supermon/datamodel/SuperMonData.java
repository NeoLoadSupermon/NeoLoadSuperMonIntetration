package com.neotys.supermon.datamodel;

import com.neotys.ascode.swagger.client.model.CustomMonitor;

import java.util.ArrayList;
import java.util.List;

public class SuperMonData extends SupermonResponse{
    SuperMonRunData data;

    ///{"status":"200 OK",
    // "responseCode":200,
    // "data":
    //    {"usecaseIdentifier":"NeoLoad_Load_test",
    //     "runSituationResult":
    //           [
    //   {"SUM_ROWS_AFFECTED":null,
    //  "SUM_SELECT_RANGE":null,
    //   "SUM_LOCK_TIME":null,
    //   "SUM_SORT_ROWS":null,
    //   "SUM_ERRORS":null,
    //    "SUM_ROWS_SENT":null,
    //     "SUM_SELECT_SCAN":null,
    //    "SUM_NO_GOOD_INDEX_USED":null,
    //    "EXEC_TIME_MAX":null,
    //     "SUM_SORT_SCAN":null,
    //      "SUM_SELECT_RANGE_CHECK":null,
    //       "USECASE_IDENTIFIER":"NeoLoad_Load_test",
    //      "SUM_TIMER_WAIT":null,
    //     "STARTTIMESTMAP":"2020-06-12T14:21:45.000+0000",
    //     "SCHEMA_NAME":"users_database",
    //        "SUM_ROWS_EXAMINED":null,
    //       "SUM_SELECT_FULL_JOIN":null,
    //      "SUM_NO_INDEX_USED":null,
    //    "COUNT_STAR":null,
    //  "SUM_SELECT_FULL_RANGE_JOIN":null,
    //  "SUM_SORT_MERGE_PASSES":null,
    //  "SUM_SORT_RANGE":null
    // }
    // ]
    // },
    // "errorMessage":null,"errorCode":null}




    public SuperMonData(String status, Integer responseCode, SuperMonRunData entries, String errmessage, String errorcode) {
        super(status, responseCode,errmessage,errorcode);
        this.data=entries;
    }


    public SuperMonRunData getData() {
        return data;
    }

    public void setData(SuperMonRunData data) {
        this.data = data;
    }

    public List<CustomMonitor> toCustomMonitor(String databaseType, String databaseName) {
        List<CustomMonitor> customMonitors=new ArrayList<>();

        data.getRunSituationResult().stream().forEach(suPerMonEntry -> {
            suPerMonEntry.toCustomMonitor(customMonitors,databaseType,databaseName);
        });

        return customMonitors;
    }
    //{
    //    "status": "200 OK",
    //    "responseCode": 200,
    //    "data": {
    //        "usecaseIdentifier": "TEST1O",
    //        "runSituationResult": [
    //            {
    //                "SUM_ROWS_AFFECTED": null,
    //                "SUM_SELECT_RANGE": null,
    //                "SUM_ROWS_SENT": null,
    //                "SUM_SELECT_SCAN": null,
    //                "SUM_NO_GOOD_INDEX_USED": null,
    //                "EXEC_TIME_MAX": null,
    //                "SUM_SORT_SCAN": null,
    //                "SUM_TIMER_WAIT": null,
    //                "SUM_ROWS_EXAMINED": null,
    //                "SUM_SELECT_FULL_JOIN": null,
    //                "ID": 49,
    //                "COUNT_STAR": null,
    //                "SUM_SELECT_FULL_RANGE_JOIN": null,
    //                "SUM_SORT_MERGE_PASSES": null,
    //                "SUM_SORT_RANGE": null,
    //                "EXEC_TIME_AVG_MS": null,
    //                "SUM_LOCK_TIME": null,
    //                "SUM_SORT_ROWS": null,
    //                "SUM_ERRORS": null,
    //                "SUM_SELECT_RANGE_CHECK": null,
    //                "USECASE_IDENTIFIER": "TEST1O",
    //                "STARTTIMESTMAP": "2020-05-18T15:23:44.000+0000",
    //                "SCHEMA_NAME": "wp",
    //                "SUM_WARNINGS": null,
    //                "SUM_NO_INDEX_USED": null,
    //                "ID_NUM": 13
    //            },
    //            {
    //                "SUM_ROWS_AFFECTED": null,
    //                "SUM_SELECT_RANGE": null,
    //                "SUM_ROWS_SENT": null,
    //                "SUM_SELECT_SCAN": null,
    //                "SUM_NO_GOOD_INDEX_USED": null,
    //                "EXEC_TIME_MAX": null,
    //                "SUM_SORT_SCAN": null,
    //                "SUM_TIMER_WAIT": null,
    //                "SUM_ROWS_EXAMINED": null,
    //                "SUM_SELECT_FULL_JOIN": null,
    //                "ID": 48,
    //                "COUNT_STAR": null,
    //                "SUM_SELECT_FULL_RANGE_JOIN": null,
    //                "SUM_SORT_MERGE_PASSES": null,
    //                "SUM_SORT_RANGE": null,
    //                "EXEC_TIME_AVG_MS": null,
    //                "SUM_LOCK_TIME": null,
    //                "SUM_SORT_ROWS": null,
    //                "SUM_ERRORS": null,
    //                "SUM_SELECT_RANGE_CHECK": null,
    //                "USECASE_IDENTIFIER": "TEST1O",
    //                "STARTTIMESTMAP": "2020-05-18T15:21:37.000+0000",
    //                "SCHEMA_NAME": "wp",
    //                "SUM_WARNINGS": null,
    //                "SUM_NO_INDEX_USED": null,
    //                "ID_NUM": 13
    //            }
    //        ]
    //    }
    //}
}
