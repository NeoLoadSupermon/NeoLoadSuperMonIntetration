package com.neotys.supermon.datamodel;

public class KPI {
    Integer newValue;
    Double deviation;
    Integer oldValue;
    String fieldName;

	//    {
	//        "fieldName": "rowsSentAvg",
	//        "newValue": 125.0000,
	//        "oldValue": 290.0000,
	//        "deviation": 56.9
	//    },


    public KPI(Integer newValue, Double deviation, Integer oldValue, String fieldName) {
        this.newValue = newValue;
        this.deviation = deviation;
        this.oldValue = oldValue;
        this.fieldName = fieldName;
    }

    public Integer getNewValue() {
        return newValue;
    }

    public void setNewValue(Integer newValue) {
        this.newValue = newValue;
    }

    public Double getDeviation() {
        return deviation;
    }

    public void setDeviation(Double deviation) {
        this.deviation = deviation;
    }

    public Integer getOldValue() {
        return oldValue;
    }

    public void setOldValue(Integer oldValue) {
        this.oldValue = oldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
