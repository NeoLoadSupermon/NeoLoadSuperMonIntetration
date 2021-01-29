package com.neotys.supermon;

import static com.neotys.supermon.conf.Constants.LOGING_LEVEL_KEY;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.neotys.ascode.api.v3.client.model.CustomMonitor;
import com.neotys.supermon.Logger.NeoLoadLogger;
import com.neotys.supermon.datamodel.SuperMonData;
import com.neotys.supermon.datamodel.SuperMonStopResponse;
import com.neotys.supermon.datamodel.SupermonStartResponse;

import io.vertx.core.Vertx;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;

public class OauthTesting {

    Vertx vertx;
    NeoLoadLogger neoloadLogger;

    @Before
    public void before()   {
        vertx = Vertx.vertx();
        neoloadLogger = new NeoLoadLogger(this.getClass().getName());
    }


    @Test
    public void convertStart()
    {
        String response="{\n" + 
        		"    \"status\": \"SUCCESS\",\n" + 
        		"    \"responseCode\": 200,\n" + 
        		"    \"data\": {\n" + 
        		"        \"idNum\": 17,\n" + 
        		"        \"usecaseIdentifier\": \"NEOLOAD-TEST1-MYSQL-RECORDING\",\n" + 
        		"        \"applicationId\": 1,\n" + 
        		"        \"applicationName\": \"Konakart MySuperMon Neoload\",\n" + 
        		"        \"applicationIdentifier\": \"4dc2c1ad-602f-4703-92d0-d4d2b787d836\",\n" + 
        		"        \"dataSourceList\": [\n" + 
        		"            {\n" + 
        		"                \"dataSourceId\": 2,\n" + 
        		"                \"databaseType\": \"MSSQL\",\n" + 
        		"                \"databaseName\": \"mykkdb\",\n" + 
        		"                \"schemaName\": \"mykkdb\",\n" + 
        		"                \"hostUrl\": \"3.124.172.28\",\n" + 
        		"                \"data\": {\n" + 
        		"                    \"TOTALNUMOFWRITES\": 5.0,\n" + 
        		"                    \"TOTALNUMOFREADS\": 118.0,\n" + 
        		"                    \"TOTALIOMB\": 5720.0,\n" + 
        		"                    \"SIZEMB\": 72.0,\n" + 
        		"                    \"TOTALNUMOFBYTESREAD\": 5808128.0,\n" + 
        		"                    \"TOTALNUMOFBYTESWRITTEN\": 49152.0,\n" + 
        		"                    \"ID_NUM\": 17.0\n" + 
        		"                },\n" + 
        		"                \"valueObjectList\": null\n" + 
        		"            }\n" + 
        		"        ]\n" + 
        		"    },\n" + 
        		"    \"errorMessage\": null,\n" + 
        		"    \"errorCode\": null,\n" + 
        		"    \"reportLink\": null\n" + 
        		"}";
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();

        SupermonStartResponse supermonStartResponse=gson.fromJson(response, SupermonStartResponse.class);
        //System.out.println(supermonStartResponse.getData().getInstanceInformation().getDatabaseType());
    }

    @Test
    public void convertRun()
    {
    	
        String response="{\"status\":\"200 OK\",\"responseCode\":200,\"data\":{\"usecaseIdentifier\":\"NeoLoad_Load_test\",\"runSituationResult\":[{\"SUM_ROWS_AFFECTED\":null,\"SUM_SELECT_RANGE\":null,\"SUM_LOCK_TIME\":null,\"SUM_SORT_ROWS\":null,\"SUM_ERRORS\":null,\"SUM_ROWS_SENT\":null,\"SUM_SELECT_SCAN\":null,\"SUM_NO_GOOD_INDEX_USED\":null,\"EXEC_TIME_MAX\":null,\"SUM_SORT_SCAN\":null,\"SUM_SELECT_RANGE_CHECK\":null,\"USECASE_IDENTIFIER\":\"NeoLoad_Load_test\",\"SUM_TIMER_WAIT\":null,\"STARTTIMESTMAP\":\"2020-06-12T14:53:46.000+0000\",\"SCHEMA_NAME\":\"users_database\",\"SUM_ROWS_EXAMINED\":null,\"SUM_SELECT_FULL_JOIN\":null,\"SUM_NO_INDEX_USED\":null,\"COUNT_STAR\":null,\"SUM_SELECT_FULL_RANGE_JOIN\":null,\"SUM_SORT_MERGE_PASSES\":null,\"SUM_SORT_RANGE\":null}]},\"errorMessage\":null,\"errorCode\":null}";

        String response2="{\n" + 
        		"    \"status\": \"200 OK\",\n" + 
        		"    \"responseCode\": 200,\n" + 
        		"    \"data\": {\n" + 
        		"        \"idNum\": 17,\n" + 
        		"        \"usecaseIdentifier\": \"NEOLOAD-TEST1-MYSQL-RECORDING\",\n" + 
        		"        \"applicationId\": 1,\n" + 
        		"        \"applicationName\": \"Konakart MySuperMon Neoload\",\n" + 
        		"        \"applicationIdentifier\": \"4dc2c1ad-602f-4703-92d0-d4d2b787d836\",\n" + 
        		"        \"runSituationResult\": [\n" + 
        		"            {\n" + 
        		"                \"dataSourceId\": 2,\n" + 
        		"                \"databaseType\": \"MSSQL\",\n" + 
        		"                \"databaseName\": \"mykkdb\",\n" + 
        		"                \"schemaName\": null,\n" + 
        		"                \"hostUrl\": \"3.124.172.28\",\n" + 
        		"                \"data\": {\n" + 
        		"                    \"WAIT_DURATION_MS\": 0,\n" + 
        		"                    \"TOTAL_ROWS\": 6470,\n" + 
        		"                    \"TOTAL_LOGICAL_READS\": 445379,\n" + 
        		"                    \"TOTAL_WORKER_TIME\": 23472643,\n" + 
        		"                    \"TOTAL_ELAPSED_TIME\": 142238879,\n" + 
        		"                    \"NUM_EXECUTIONS\": 3741,\n" + 
        		"                    \"LOCK_TOTAL_ELAPSED_TIME\": 0,\n" + 
        		"                    \"STARTTIMESTAMP\": \"2021-01-29T09:20:30.557+0000\",\n" + 
        		"                    \"TOTAL_PHYSICAL_READS\": 560,\n" + 
        		"                    \"TOTAL_LOGICAL_WRITES\": 9403\n" + 
        		"                }\n" + 
        		"            }\n" + 
        		"        ]\n" + 
        		"    },\n" + 
        		"    \"errorMessage\": null,\n" + 
        		"    \"errorCode\": null,\n" + 
        		"    \"reportLink\": null\n" + 
        		"}";

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
        SuperMonData supermonStartResponse=gson.fromJson(response2, SuperMonData.class);
        List<CustomMonitor> customMonitorList = supermonStartResponse.toCustomMonitor(neoloadLogger);
        System.out.println(supermonStartResponse.getData().getUsecaseIdentifier());

    }
    
    @Test
    public void convertStop()
    {
        String response="{\"status\":\"200 OK\",\"responseCode\":200,\"data\":{\"usecaseIdentifier\":\"NeoLoad_Load_test\",\"runSituationResult\":[{\"SUM_ROWS_AFFECTED\":null,\"SUM_SELECT_RANGE\":null,\"SUM_LOCK_TIME\":null,\"SUM_SORT_ROWS\":null,\"SUM_ERRORS\":null,\"SUM_ROWS_SENT\":null,\"SUM_SELECT_SCAN\":null,\"SUM_NO_GOOD_INDEX_USED\":null,\"EXEC_TIME_MAX\":null,\"SUM_SORT_SCAN\":null,\"SUM_SELECT_RANGE_CHECK\":null,\"USECASE_IDENTIFIER\":\"NeoLoad_Load_test\",\"SUM_TIMER_WAIT\":null,\"STARTTIMESTMAP\":\"2020-06-12T14:53:46.000+0000\",\"SCHEMA_NAME\":\"users_database\",\"SUM_ROWS_EXAMINED\":null,\"SUM_SELECT_FULL_JOIN\":null,\"SUM_NO_INDEX_USED\":null,\"COUNT_STAR\":null,\"SUM_SELECT_FULL_RANGE_JOIN\":null,\"SUM_SORT_MERGE_PASSES\":null,\"SUM_SORT_RANGE\":null}]},\"errorMessage\":null,\"errorCode\":null}";

        String response2="{\n" + 
        		"    \"status\": \"SUCCESS\",\n" + 
        		"    \"responseCode\": 200,\n" + 
        		"    \"data\": {\n" + 
        		"        \"applicationId\": 1,\n" + 
        		"        \"applicationName\": \"Konakart MySuperMon Neoload\",\n" + 
        		"        \"applicationIdentifier\": \"4dc2c1ad-602f-4703-92d0-d4d2b787d836\",\n" + 
        		"        \"idNum\": 17,\n" + 
        		"        \"usecaseIdentifier\": \"NEOLOAD-TEST1-MYSQL-RECORDING\",\n" + 
        		"        \"startTimestamp\": \"2021-01-29T14:49:21.000+0000\",\n" + 
        		"        \"dataSourceList\": [\n" + 
        		"            {\n" + 
        		"                \"dataSourceId\": 2,\n" + 
        		"                \"databaseType\": \"MSSQL\",\n" + 
        		"                \"databaseName\": \"mykkdb\",\n" + 
        		"                \"schemaName\": null,\n" + 
        		"                \"hostUrl\": \"mykkdb\",\n" + 
        		"                \"data\": {\n" + 
        		"                    \"TOTALNUMOFWRITES\": 5.0,\n" + 
        		"                    \"TOTALNUMOFREADS\": 118.0,\n" + 
        		"                    \"TOTALIOMB\": 5720.0,\n" + 
        		"                    \"SIZEMB\": 72.0,\n" + 
        		"                    \"TOTALNUMOFBYTESREAD\": 5808128.0,\n" + 
        		"                    \"TOTALNUMOFBYTESWRITTEN\": 49152.0,\n" + 
        		"                    \"ID_NUM\": 17.0\n" + 
        		"                },\n" + 
        		"                \"valueObjectList\": []\n" + 
        		"            }\n" + 
        		"        ]\n" + 
        		"    },\n" + 
        		"    \"errorMessage\": null,\n" + 
        		"    \"errorCode\": null,\n" + 
        		"    \"reportLink\": \"http://localhost/#/report/NEOLOAD-TEST1-MYSQL-RECORDING/17\"\n" + 
        		"}";

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();

        SuperMonStopResponse supermonStartResponse=gson.fromJson(response2, SuperMonStopResponse.class);

        System.out.println(supermonStartResponse.getData().getUsecaseIdentifier());

    }
    
    @Test
    public void testtime() throws ParseException {
       DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date result=m_ISO8601Local.parse("2020-05-15T09:27:32.000+0000");
        System.out.println(result.getTime());
    }
    @Test
    public void testOaut()
    {


    }
}
