package com.neotys.supermonContext.datamodel;

public class NeoLoadSuperMonDescription {

    //---------------
    /*          field description stored
    {
     	applicationIdentifier : ,
	    useCaseIdentifier:

    }
    */
    //--------------
    String applicationIdentifier;
    String useCaseIdentifier;


    public NeoLoadSuperMonDescription(String schemeID, String usecase) {
        this.applicationIdentifier = schemeID;
        this.useCaseIdentifier=usecase;
    }



    public String getSchemeID() {
        return applicationIdentifier;
    }

    public void setSchemeID(String schemeID) {
        this.applicationIdentifier = schemeID;
    }


    public String getUseCaseIdentifier() {
        return useCaseIdentifier;
    }

    public void setUseCaseIdentifier(String useCaseIdentifier) {
        this.useCaseIdentifier = useCaseIdentifier;
    }
}
