package com.neotys.supermon.datamodel;

public class AlertConfig {
    Integer id;
    Integer applicationId;
    Integer configId;
    String columnName;
    String columnTitle;
    String updatedTimestamp;

    public AlertConfig(Integer id, Integer applicationId, Integer configId, String columnName, String columnTitle, String updatedTimestamp) {
        this.id = id;
        this.applicationId = applicationId;
        this.configId = configId;
        this.columnName = columnName;
        this.columnTitle = columnTitle;
        this.updatedTimestamp = updatedTimestamp;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnTitle() {
		return columnTitle;
	}

	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
	}

	public String getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(String updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}


    //                     "id":0,
        //                     "applicationId":10,
        //                     "configId":1,
        //                     "columnName":"totalExecutionTime",
        //                     "fieldName":"Total Execution Time",
        //                     "updatedTimestamp":null
        //                  },
}
