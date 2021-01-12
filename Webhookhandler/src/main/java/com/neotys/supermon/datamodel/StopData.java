package com.neotys.supermon.datamodel;

import java.util.Date;
import java.util.List;

public class StopData {	
    private long idNum;
    private long applicationId;
    private String applicationName;
    private String applicationIdentifier;
    private List<DataSourceData> dataSourceList;
    private String applicationUrl;
    
	private String usecaseIdentifier;
	private Date startTimestamp;
	
	public StopData(long applicationId, String applicationName, String applicationIdentifier, long idNum,
			String usecaseIdentifier, Date startTimestamp, List<DataSourceData> dataSourceList, String applicationUrl) {
		super();
		this.applicationId = applicationId;
		this.applicationName = applicationName;
		this.applicationIdentifier = applicationIdentifier;
		this.idNum = idNum;
		this.usecaseIdentifier = usecaseIdentifier;
		this.startTimestamp = startTimestamp;
		this.dataSourceList = dataSourceList;
		this.applicationUrl = applicationUrl;
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
	public long getIdNum() {
		return idNum;
	}
	public void setIdNum(long idNum) {
		this.idNum = idNum;
	}
	public String getUsecaseIdentifier() {
		return usecaseIdentifier;
	}
	public void setUsecaseIdentifier(String usecaseIdentifier) {
		this.usecaseIdentifier = usecaseIdentifier;
	}
	public Date getStartTimestamp() {
		return startTimestamp;
	}
	public void setStartTimestamp(Date startTimestamp) {
		this.startTimestamp = startTimestamp;
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
