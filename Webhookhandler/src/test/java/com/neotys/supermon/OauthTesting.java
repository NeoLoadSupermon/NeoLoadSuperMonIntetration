package com.neotys.supermon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.neotys.supermon.datamodel.SuperMonData;
import com.neotys.supermon.datamodel.SupermonStartResponse;
import io.vertx.core.Vertx;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import static com.neotys.supermon.conf.Constants.HTTPS;

public class OauthTesting {

    Vertx vertx;

    @Before
    public void before()   {
        vertx = Vertx.vertx();

    }


    @Test
    public void convertStart()
    {
        String response="{\"status\":\"SUCCESS\",\"responseCode\":200,\"data\":{\"instanceInformation\":{\"usecaseIdentifier\":\"NeoLoad_Load_test\",\"databaseType\":\"MYSQL\",\"instanceName\":\"172.17.0.1\",\"databaseName\":\"users_database\",\"schemaName\":null,\"instanceInformationDetails\":{\"TOTAL_MEMORY_ALLOCATED_IN_MB\":0.0,\"STATEMENTS\":15406.0,\"STATEMENT_LATENCY_IN_S\":4.66,\"FILE_IO_LATENCY_IN_S\":1.79,\"CURRENT_CONNECTIONS\":20.0,\"DATABASE_SIZE_IN_MB\":0.0,\"STATEMENT_AVG_LATENCY_IN_MS\":0.3,\"FILE_IOS\":4102.0,\"TOTAL_CONNECTIONS\":2114.0,\"TABLE_SCANS\":224.0,\"CURRENT_MEMORY_IN_MB\":0.0,\"UNIQUE_USERS\":1.0}},\"data\":true,\"applicationUrl\":\"http://3.1.221.225:8110/mySuperMon/\"},\"errorMessage\":null,\"errorCode\":null}";
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();

        SupermonStartResponse supermonStartResponse=gson.fromJson(response, SupermonStartResponse.class);
        //System.out.println(supermonStartResponse.getData().getInstanceInformation().getDatabaseType());
    }

    @Test
    public void convertRun()
    {
        String response="{\"status\":\"200 OK\",\"responseCode\":200,\"data\":{\"usecaseIdentifier\":\"NeoLoad_Load_test\",\"runSituationResult\":[{\"SUM_ROWS_AFFECTED\":null,\"SUM_SELECT_RANGE\":null,\"SUM_LOCK_TIME\":null,\"SUM_SORT_ROWS\":null,\"SUM_ERRORS\":null,\"SUM_ROWS_SENT\":null,\"SUM_SELECT_SCAN\":null,\"SUM_NO_GOOD_INDEX_USED\":null,\"EXEC_TIME_MAX\":null,\"SUM_SORT_SCAN\":null,\"SUM_SELECT_RANGE_CHECK\":null,\"USECASE_IDENTIFIER\":\"NeoLoad_Load_test\",\"SUM_TIMER_WAIT\":null,\"STARTTIMESTMAP\":\"2020-06-12T14:53:46.000+0000\",\"SCHEMA_NAME\":\"users_database\",\"SUM_ROWS_EXAMINED\":null,\"SUM_SELECT_FULL_JOIN\":null,\"SUM_NO_INDEX_USED\":null,\"COUNT_STAR\":null,\"SUM_SELECT_FULL_RANGE_JOIN\":null,\"SUM_SORT_MERGE_PASSES\":null,\"SUM_SORT_RANGE\":null}]},\"errorMessage\":null,\"errorCode\":null}";

        String response2="{\"status\":\"200 OK\",\"responseCode\":200,\"data\":{\"usecaseIdentifier\":\"NeoLoad_Load_test\",\"runSituationResult\":[{\"SUM_ROWS_AFFECTED\":125.0,\"SUM_SELECT_RANGE\":221.21,\"SUM_LOCK_TIME\":null,\"SUM_SORT_ROWS\":null,\"SUM_ERRORS\":12.0,\"SUM_ROWS_SENT\":null,\"SUM_SELECT_SCAN\":null,\"SUM_NO_GOOD_INDEX_USED\":null,\"EXEC_TIME_MAX\":null,\"SUM_SORT_SCAN\":null,\"SUM_SELECT_RANGE_CHECK\":null,\"USECASE_IDENTIFIER\":\"NeoLoad_Load_test\",\"SUM_TIMER_WAIT\":null,\"STARTTIMESTMAP\":\"2020-06-12T14:53:46.000+0000\",\"SCHEMA_NAME\":\"users_database\",\"SUM_ROWS_EXAMINED\":null,\"SUM_SELECT_FULL_JOIN\":null,\"SUM_NO_INDEX_USED\":null,\"COUNT_STAR\":null,\"SUM_SELECT_FULL_RANGE_JOIN\":null,\"SUM_SORT_MERGE_PASSES\":null,\"SUM_SORT_RANGE\":null}]},\"errorMessage\":null,\"errorCode\":null}";

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();

        SuperMonData supermonStartResponse=gson.fromJson(response2, SuperMonData.class);

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
