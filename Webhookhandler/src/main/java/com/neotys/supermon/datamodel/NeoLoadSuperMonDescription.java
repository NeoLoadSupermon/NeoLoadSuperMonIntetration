package com.neotys.supermon.datamodel;

public class NeoLoadSuperMonDescription {

    //---------------
    /* field description stored

    {"applicationIdentifier":"48f85bf4-3ff7-42e3-b56d-e11e7dfae752","databaseType":"Mysql","useCaseIdentifier":"NeoLoad_Load_test"}
    {
     	applicationIdentifier : ,
	    databaseType :
	    databaseName:
	    useCaseIdentifier:
    }
    */
    //--------------
    String applicationIdentifier;
    String databaseType;
    String databaseName;
    String useCaseIdentifier;

    public NeoLoadSuperMonDescription(String schemeID, String databaseType, String databaseName,String usecase) {
        this.applicationIdentifier = schemeID;
        this.databaseType = databaseType;
        this.databaseName = databaseName;
        this.useCaseIdentifier = usecase;
    }

    public NeoLoadSuperMonDescription(String applicationIdentifier, com.google.common.base.Optional<String> databaseType, com.google.common.base.Optional<String> databaseName,String useCaseIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
        if(databaseType.isPresent())
            this.databaseType = databaseType.get();
        else
            this.databaseType = null;

        if(databaseName.isPresent())
            this.databaseName = databaseName.get();
        else
            this.databaseName = null;

        this.useCaseIdentifier=useCaseIdentifier;



    }


    public String getApplicationIdentifier() {
        return applicationIdentifier;
    }

    public void setApplicationIdentifier(String applicationIdentifier) {
        this.applicationIdentifier = applicationIdentifier;
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

