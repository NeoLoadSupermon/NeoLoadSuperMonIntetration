package com.neotys.supermon.datamodel;

import java.util.HashMap;
import java.util.List;

public class TestData {
//"data":[
//    //         {
//    //            "usecaseIdentifier":"NeoLoad_Load_test",
//    //            "idNum":16,
//    //            "startTimestamp":"2020-07-30T12:23:20.000+0000",
//    //            "endTimestamp":"2020-07-30T12:28:23.000+0000",
//    //            "valueObjects":[
//    //               {
//    //                  "fieldName":"avgTimerWait",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"execCount",
//    //                  "newValue":8577,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"execTimeAvgMS",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"execTimeMax",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"execTimeTotal",
//    //                  "newValue":3,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"maxTimerWait",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"minTimerWait",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"rowsSentAvg",
//    //                  "newValue":1371,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumCreatedTmpDiskTables",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumCreatedTmpTables",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumErrors",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumLockTime",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumNoGoodIndexUsed",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumNoIndexUsed",
//    //                  "newValue":1478,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumRowsAffected",
//    //                  "newValue":471,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumRowsExamined",
//    //                  "newValue":1940982,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumRowsSent",
//    //                  "newValue":1940204,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumSelectFullJoin",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumSelectFullRangeJoin",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumSelectRange",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumSelectRangeCheck",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumSelectScan",
//    //                  "newValue":1478,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumSortMergePasses",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumSortRange",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumSortRow",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumSortScan",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumTimerWait",
//    //                  "newValue":3,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               },
//    //               {
//    //                  "fieldName":"sumWarnings",
//    //                  "newValue":0,
//    //                  "oldValue":0,
//    //                  "deviation":0.0
//    //               }
//    //            ]
//    //         }
//    //      ],
//    //      "applicationUrl":"http://3.1.221.225:8110/mySuperMon/report/1596112111417/index.html"
//    //   },
//    //   "errorMessage":null,
//    //   "errorCode":null
//    //}"\"}"

       String usecaseIdentifier;
       Integer idNum;
       String starttimestamp;
       String endtimestamp;
       List<KPI> valueObjects;

    public TestData(String usecaseIdentifier, Integer idNum, String starttimestamp, String endtimestamp, List<KPI> valueObjects) {
        this.usecaseIdentifier = usecaseIdentifier;
        this.idNum = idNum;
        this.starttimestamp = starttimestamp;
        this.endtimestamp = endtimestamp;
        this.valueObjects = valueObjects;
    }

    public String getUsecaseIdentifier() {
        return usecaseIdentifier;
    }

    public void setUsecaseIdentifier(String usecaseIdentifier) {
        this.usecaseIdentifier = usecaseIdentifier;
    }

    public Integer getIdNum() {
        return idNum;
    }

    public void setIdNum(Integer idNum) {
        this.idNum = idNum;
    }

    public String getStarttimestamp() {
        return starttimestamp;
    }

    public void setStarttimestamp(String starttimestamp) {
        this.starttimestamp = starttimestamp;
    }

    public String getEndtimestamp() {
        return endtimestamp;
    }

    public void setEndtimestamp(String endtimestamp) {
        this.endtimestamp = endtimestamp;
    }

    public List<KPI> getValueObjects() {
        return valueObjects;
    }

    public void setValueObjects(List<KPI> valueObjects) {
        this.valueObjects = valueObjects;
    }
}
