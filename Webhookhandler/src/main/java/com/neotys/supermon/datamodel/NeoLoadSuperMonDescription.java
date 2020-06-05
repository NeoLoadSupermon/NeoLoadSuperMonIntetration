package com.neotys.supermon.datamodel;

import java.util.HashMap;
import java.util.List;


public class NeoLoadSuperMonDescription {

    //---------------
    /*          field description stored

    {"schemeID":"MYSQL_mysql_agent_7491","databaseType":"Mysql","useCaseIdentifier":"NeoLoad_Load_test"}
    {
     schemeID : ,
    databaseType :
    databaseName:
    useCaseIdentifier:
    }
    */
    //--------------
    String schemeID;
    String databaseType;
    String databaseName;
    String useCaseIdentifier;

    public NeoLoadSuperMonDescription(String schemeID, String databaseType, String databaseName,String usecase) {
        this.schemeID = schemeID;
        this.databaseType = databaseType;
        this.databaseName = databaseName;
        this.useCaseIdentifier=usecase;
    }

    public NeoLoadSuperMonDescription(String schemeID, com.google.common.base.Optional<String> databaseType, com.google.common.base.Optional<String> databaseName,String useCaseIdentifier) {
        this.schemeID = schemeID;
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
        return schemeID;
    }

    public void setSchemeID(String schemeID) {
        this.schemeID = schemeID;
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

