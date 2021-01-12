package com.neotys.supermon.datamodel;

import java.util.ArrayList;
import java.util.List;

public class StoptStatistics {
    String usecaseIdentifier;
    String starttimestamp;
    String endtimestamp;
    String id_num;
    Integer id;
    Integer rowNumber;
    List<KPI> valueObjectList=new ArrayList<>();
    Integer resultInPercentage;
    String result;
    String usecaseResult;
    String latestUsecaseStartTimestamp;
    String oldUsecaseStartTimestamp;
    Integer batchId;
    Integer dataSourceId;
    Integer statusId;
    Integer configId;
    List<AlertConfig> alertConfigInfoList=new ArrayList<>();
    AlertCriteriaModel alertCriteriaModel;
    Integer responseTime;

        //               "usecaseIdentifier":"NeoLoad_Load_test",
        //               "starttimestamp":"2020-07-08T07:08:34.000+0000",
        //               "endtimestamp":null,
        //               "id_num":null,
        //               "id":0,
        //               "rowNumber":0,
        //               "valueObjectList":[

        //                  ..]
        //               "resultInPercentage":-17024,
        //               "result":"FAILURE",
        //               "usecaseResult":null,
        //               "latestUsecaseStartTimestamp":"2020-07-08T07:08:34.000+0000",
        //               "oldUsecaseStartTimestamp":"2020-07-08T07:07:37.000+0000",
        //               "batchId":53,
        //               "dataSourceId":10,
        //               "statusId":0,
        //               "configId":1,
        //               "alertConfigInfoList"[

        //               ],
        //               "alertCriteriaModel":{

        //               },
        //               "responseTime":602
        //            }


    public StoptStatistics(String usecaseIdentifier, String starttimestamp, String endtimestamp, String id_num, Integer id, Integer rowNumber, List<KPI> valueObjectList, Integer resultInPercentage, String result, String usecaseResult, String latestUsecaseStartTimestamp, String oldUsecaseStartTimestamp, Integer batchId, Integer dataSourceId, Integer statusId, Integer configId, List<AlertConfig> alertConfigInfoList, AlertCriteriaModel alertCriteriaModel, Integer responseTime) {
        this.usecaseIdentifier = usecaseIdentifier;
        this.starttimestamp = starttimestamp;
        this.endtimestamp = endtimestamp;
        this.id_num = id_num;
        this.id = id;
        this.rowNumber = rowNumber;
        this.valueObjectList = valueObjectList;
        this.resultInPercentage = resultInPercentage;
        this.result = result;
        this.usecaseResult = usecaseResult;
        this.latestUsecaseStartTimestamp = latestUsecaseStartTimestamp;
        this.oldUsecaseStartTimestamp = oldUsecaseStartTimestamp;
        this.batchId = batchId;
        this.dataSourceId = dataSourceId;
        this.statusId = statusId;
        this.configId = configId;
        this.alertConfigInfoList = alertConfigInfoList;
        this.alertCriteriaModel = alertCriteriaModel;
        this.responseTime = responseTime;
    }

    public String getUsecaseIdentifier() {
        return usecaseIdentifier;
    }

    public void setUsecaseIdentifier(String usecaseIdentifier) {
        this.usecaseIdentifier = usecaseIdentifier;
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

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public List<KPI> getValueObjectList() {
        return valueObjectList;
    }

    public void setValueObjectList(List<KPI> valueObjectList) {
        this.valueObjectList = valueObjectList;
    }

    public Integer getResultInPercentage() {
        return resultInPercentage;
    }

    public void setResultInPercentage(Integer resultInPercentage) {
        this.resultInPercentage = resultInPercentage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUsecaseResult() {
        return usecaseResult;
    }

    public void setUsecaseResult(String usecaseResult) {
        this.usecaseResult = usecaseResult;
    }

    public String getLatestUsecaseStartTimestamp() {
        return latestUsecaseStartTimestamp;
    }

    public void setLatestUsecaseStartTimestamp(String latestUsecaseStartTimestamp) {
        this.latestUsecaseStartTimestamp = latestUsecaseStartTimestamp;
    }

    public String getOldUsecaseStartTimestamp() {
        return oldUsecaseStartTimestamp;
    }

    public void setOldUsecaseStartTimestamp(String oldUsecaseStartTimestamp) {
        this.oldUsecaseStartTimestamp = oldUsecaseStartTimestamp;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Integer dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public List<AlertConfig> getAlertConfigInfoList() {
        return alertConfigInfoList;
    }

    public void setAlertConfigInfoList(List<AlertConfig> alertConfigInfoList) {
        this.alertConfigInfoList = alertConfigInfoList;
    }

    public AlertCriteriaModel getAlertCriteriaModel() {
        return alertCriteriaModel;
    }

    public void setAlertCriteriaModel(AlertCriteriaModel alertCriteriaModel) {
        this.alertCriteriaModel = alertCriteriaModel;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
    }
}
