package com.neotys.supermon.datamodel;

public class MySuperMonThresholdDefinition {
    //errorThrueshold :
    ////							   warningThreshold :

    String errorThrueshold;
    String warningThreshold;

    public MySuperMonThresholdDefinition(String errorThrueshold, String warningThreshold) {
        this.errorThrueshold = errorThrueshold;
        this.warningThreshold = warningThreshold;
    }

    public String getErrorThrueshold() {
        return errorThrueshold;
    }

    public void setErrorThrueshold(String errorThrueshold) {
        this.errorThrueshold = errorThrueshold;
    }

    public String getWarningThreshold() {
        return warningThreshold;
    }

    public void setWarningThreshold(String warningThreshold) {
        this.warningThreshold = warningThreshold;
    }
}
