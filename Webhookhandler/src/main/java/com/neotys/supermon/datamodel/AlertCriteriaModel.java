package com.neotys.supermon.datamodel;

public class AlertCriteriaModel {
    Integer statusId;
    Integer datasourceId;
    Integer failure;
    Integer warning;
    Integer sucess;
    Integer improvements;
    String updatedTiemstamp;
    String updatedBy;
    //                  "statusId":10,
    //                  "datasourceId":10,
    //                  "failure":-20,
    //                  "warning":0,
    //                  "success":20,
    //                  "improvements":20,
    //                  "updatedTiemstamp":null,
    //                  "updatedBy":null


    public AlertCriteriaModel(Integer statusId, Integer datasourceId, Integer failure, Integer warning, Integer sucess, Integer improvements, String updatedTiemstamp, String updatedBy) {
        this.statusId = statusId;
        this.datasourceId = datasourceId;
        this.failure = failure;
        this.warning = warning;
        this.sucess = sucess;
        this.improvements = improvements;
        this.updatedTiemstamp = updatedTiemstamp;
        this.updatedBy = updatedBy;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(Integer datasourceId) {
        this.datasourceId = datasourceId;
    }

    public Integer getFailure() {
        return failure;
    }

    public void setFailure(Integer failure) {
        this.failure = failure;
    }

    public Integer getWarning() {
        return warning;
    }

    public void setWarning(Integer warning) {
        this.warning = warning;
    }

    public Integer getSucess() {
        return sucess;
    }

    public void setSucess(Integer sucess) {
        this.sucess = sucess;
    }

    public Integer getImprovements() {
        return improvements;
    }

    public void setImprovements(Integer improvements) {
        this.improvements = improvements;
    }

    public String getUpdatedTiemstamp() {
        return updatedTiemstamp;
    }

    public void setUpdatedTiemstamp(String updatedTiemstamp) {
        this.updatedTiemstamp = updatedTiemstamp;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
