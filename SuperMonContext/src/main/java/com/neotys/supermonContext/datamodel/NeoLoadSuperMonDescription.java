package com.neotys.supermonContext.datamodel;

public class NeoLoadSuperMonDescription {

    //---------------
    /*          field description stored
    {
     	applicationIdentifier : ,
	    useCaseIdentifier:
	    databaseType :
	    databaseName:
    }
    */
    //--------------
    String applicationIdentifier;
    String useCaseIdentifier;
    String databaseType;
    String databaseName;
    

    public NeoLoadSuperMonDescription(String schemeID, String databaseType, String databaseName,String usecase) {
        this.applicationIdentifier = schemeID;
        this.databaseType = databaseType;
        this.databaseName = databaseName;
        this.useCaseIdentifier=usecase;
    }

    public NeoLoadSuperMonDescription(String schemeID, com.google.common.base.Optional<String> databaseType, com.google.common.base.Optional<String> databaseName,String useCaseIdentifier) {
        this.applicationIdentifier = schemeID;
        if(databaseType.isPresent())
            this.databaseType=databaseType.get();
        else
            this.databaseType=null;

        if(databaseName.isPresent())
            this.databaseName=databaseName.get();
        else
            this.databaseName=null;

        this.useCaseIdentifier=useCaseIdentifier;
   }


    public String getSchemeID() {
        return applicationIdentifier;
    }

    public void setSchemeID(String schemeID) {
        this.applicationIdentifier = schemeID;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getUseCaseIdentifier() {
        return useCaseIdentifier;
    }

    public void setUseCaseIdentifier(String useCaseIdentifier) {
        this.useCaseIdentifier = useCaseIdentifier;
    }
}
