package com.neotys.supermon.datamodel;

import java.util.List;

public class StartData {
    private long idNum;
    private long applicationId;
    private String applicationName;
    private String applicationIdentifier;
    private List<DataSourceData> dataSourceList;
    private String applicationUrl;
    
   	public StartData(long idNum, long applicationId, String applicationName, String applicationIdentifier, List<DataSourceData> dataSourceList) {
		super();
		this.idNum = idNum;
		this.applicationId = applicationId;
		this.applicationName = applicationName;
		this.applicationIdentifier = applicationIdentifier;
		this.dataSourceList = dataSourceList;
	}

	public long getIdNum() {
		return idNum;
	}

	public void setIdNum(long idNum) {
		this.idNum = idNum;
	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationIdentifier() {
		return applicationIdentifier;
	}

	public void setApplicationIdentifier(String applicationIdentifier) {
		this.applicationIdentifier = applicationIdentifier;
	}

	public List<DataSourceData> getDataSourceList() {
		return dataSourceList;
	}

	public void setDataSourceList(List<DataSourceData> dataSourceList) {
		this.dataSourceList = dataSourceList;
	}

	public String getApplicationUrl() {
		return applicationUrl;
	}

	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

}
