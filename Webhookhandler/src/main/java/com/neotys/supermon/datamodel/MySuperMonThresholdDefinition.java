package com.neotys.supermon.datamodel;

public class MySuperMonThresholdDefinition {
    //errorThrueshold :
    ////							   warningThreshold :

    String errorThreshold;
    String warningThreshold;

    public MySuperMonThresholdDefinition(String errorThrueshold, String warningThreshold) {
        this.errorThreshold = errorThrueshold;
        this.warningThreshold = warningThreshold;
    }

    public String getErrorThrueshold() {
        return errorThreshold;
    }

    public void setErrorThrueshold(String errorThrueshold) {
        this.errorThreshold = errorThrueshold;
    }

    public String getWarningThreshold() {
        return warningThreshold;
    }

    public void setWarningThreshold(String warningThreshold) {
        this.warningThreshold = warningThreshold;
    }
}
