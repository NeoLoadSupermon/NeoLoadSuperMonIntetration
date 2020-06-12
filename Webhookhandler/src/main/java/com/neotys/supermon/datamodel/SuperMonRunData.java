package com.neotys.supermon.datamodel;

import java.util.ArrayList;
import java.util.List;

public class SuperMonRunData {
    String usecaseIdentifier;
    List<SuPerMonEntry> runSituationResult= new ArrayList();

    public SuperMonRunData(String usecaseIdentifier, List<SuPerMonEntry> runSituationResult) {
        this.usecaseIdentifier = usecaseIdentifier;
        this.runSituationResult = runSituationResult;
    }

    public String getUsecaseIdentifier() {
        return usecaseIdentifier;
    }

    public void setUsecaseIdentifier(String usecaseIdentifier) {
        this.usecaseIdentifier = usecaseIdentifier;
    }

    public List<SuPerMonEntry> getRunSituationResult() {
        return runSituationResult;
    }

    public void setRunSituationResult(List<SuPerMonEntry> runSituationResult) {
        this.runSituationResult = runSituationResult;
    }
}
