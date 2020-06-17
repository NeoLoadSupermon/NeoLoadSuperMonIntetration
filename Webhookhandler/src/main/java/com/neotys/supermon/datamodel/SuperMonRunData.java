package com.neotys.supermon.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SuperMonRunData {
    String usecaseIdentifier;
    List<Map<String, Object>> runSituationResult;
    List<SuPerMonEntry> runIDSituationEntries = new ArrayList<>();

    public List<SuPerMonEntry> getRunIDSituationEntries() {
        return runIDSituationEntries;
    }

    public void setRunIDSituationEntries(List<SuPerMonEntry> runIDSituationEntries) {
        this.runIDSituationEntries = runIDSituationEntries;
    }


    public SuperMonRunData(String usecaseIdentifier, List<Map<String, Object>> runSituationResult) {
        this.usecaseIdentifier = usecaseIdentifier;
        this.runSituationResult = runSituationResult;
    }

    public List<Map<String, Object>> getRunSituationResult() {
        return runSituationResult;
    }

    public void setRunSituationResult(List<Map<String, Object>> runSituationResult) {
        this.runSituationResult = runSituationResult;
    }

    public void convertToSuperMonEntry() {
        this.runIDSituationEntries=new ArrayList<>();
        runSituationResult.stream().forEach(stringObjectMap -> {
            if(stringObjectMap!=null)
                runIDSituationEntries.add(new SuPerMonEntry(stringObjectMap));
        });
    }

    public String getUsecaseIdentifier() {
        return usecaseIdentifier;
    }

    public void setUsecaseIdentifier(String usecaseIdentifier) {
        this.usecaseIdentifier = usecaseIdentifier;
    }

}
