package com.neotys.supermon.datamodel;

import java.util.ArrayList;
import java.util.List;

public class SuperMonRunData {
	private long idNum;
    private String usecaseIdentifier;
    private long applicationId;
    private String applicationName;
    private String applicationIdentifier;
    
    List<DataSourceData> runSituationResult;
    List<SuPerMonEntry> runIDSituationEntries = new ArrayList<>();
    
	public SuperMonRunData(long idNum, String usecaseIdentifier, long applicationId, String applicationName,
			String applicationIdentifier, List<DataSourceData> runSituationResult,
			List<SuPerMonEntry> runIDSituationEntries) {
		super();
		this.idNum = idNum;
		this.usecaseIdentifier = usecaseIdentifier;
		this.applicationId = applicationId;
		this.applicationName = applicationName;
		this.applicationIdentifier = applicationIdentifier;
		this.runSituationResult = runSituationResult;
		this.runIDSituationEntries = runIDSituationEntries;
	}
	
    public void convertToSuperMonEntry() {
        this.runIDSituationEntries=new ArrayList<>();
        runSituationResult.stream().forEach(stringObjectMap -> {
            if(stringObjectMap!=null)
                runIDSituationEntries.add(new SuPerMonEntry(stringObjectMap.getData()));
        });
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
	public List<DataSourceData> getRunSituationResult() {
		return runSituationResult;
	}
	public void setRunSituationResult(List<DataSourceData> runSituationResult) {
		this.runSituationResult = runSituationResult;
	}
	public List<SuPerMonEntry> getRunIDSituationEntries() {
		return runIDSituationEntries;
	}
	public void setRunIDSituationEntries(List<SuPerMonEntry> runIDSituationEntries) {
		this.runIDSituationEntries = runIDSituationEntries;
	}
    
}
