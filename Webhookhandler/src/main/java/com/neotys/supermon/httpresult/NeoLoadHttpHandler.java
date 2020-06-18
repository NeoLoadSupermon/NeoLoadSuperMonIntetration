package com.neotys.supermon.httpresult;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.neotys.ascode.swagger.client.ApiClient;
import com.neotys.ascode.swagger.client.ApiException;
import com.neotys.ascode.swagger.client.api.ResultsApi;
import com.neotys.ascode.swagger.client.model.*;
import com.neotys.supermon.Logger.NeoLoadLogger;
import com.neotys.supermon.common.NeoLoadException;
import com.neotys.supermon.conf.Constants.*;
import com.neotys.supermon.datamodel.NeoLoadSuperMonDescription;
import com.neotys.supermon.datamodel.SuperMonData;
import com.neotys.supermon.datamodel.SuperMonStopResponse;
import com.neotys.supermon.datamodel.SupermonStartResponse;
import com.neotys.supermon.httpclient.Credentials;
import com.neotys.supermon.httpclient.Httpclient;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;
import rx.Completable;
import rx.Scheduler;
import rx.Single;
import rx.Subscription;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.neotys.supermon.conf.Constants.*;

public class NeoLoadHttpHandler {
    private String testid;
    private Optional<String> neoload_Web_Url;
    private Optional<String> neoload_API_Url;
    private String neoload_API_key;
    private NeoLoadLogger logger;
    private ApiClient apiClient;

    private Optional<String> cloudport;
    private Optional<String> cloudhost;
    private Optional<String> clien_id;
    private Optional<String> client_secret;
    private Optional<String> user;
    private Optional<String> password;
    private Optional<String> cloud_api_Path;
    private Optional<String> cloud_Oauth_token_path;
    private ResultsApi resultsApi;
    private Credentials credentials;
    private boolean ssl;
    private String schemeid;
    private String usecase;
    private String databaseType;
    private String databaseName;
    private Subscription subscription;

    public NeoLoadHttpHandler(String testid ) throws NeoLoadException {
        this.testid=testid;
        logger=new NeoLoadLogger(this.getClass().getName());
        logger.setTestid(testid);
        getEnvVariables();
        generateApiUrl();

        apiClient=new ApiClient();
        apiClient.setBasePath(HTTPS+neoload_API_Url.get());
        apiClient.setApiKey(neoload_API_key);
        resultsApi=new ResultsApi(apiClient);
        credentials=new Credentials(user.get(),password.get(),clien_id.get(),client_secret.get(),"read");
    }

    private NeoLoadSuperMonDescription getSuperMonDescriptionFromTest(String description) throws JsonSyntaxException, ApiException, InterruptedException, NeoLoadException {
        if(description!=null)
        {
            if(description.isEmpty()||description.trim().isEmpty())
            {
                logger.debug("Description is currently empty--let's wait");
                Thread.sleep(2000);
                description=resultsApi.getTest(testid).getDescription();
                logger.debug("descritpion retrieved "+ description);
            }

            if(description.isEmpty()||description.trim().isEmpty())
                throw new NeoLoadException("the NeoLoad is empty");
            logger.debug("Converting Description into java Object ->    "+ description);
            Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
            NeoLoadSuperMonDescription superMonDescription = gson.fromJson(description, NeoLoadSuperMonDescription.class);
            logger.debug("description converted - scheme : "+ superMonDescription.getSchemeID());
            return superMonDescription;
        }
        else
            return null;
    }

    public Future<String> stopRecording(Vertx vertx)
    {
        Future<String> futureresult=Future.future();

        if(subscription!=null)
            subscription.unsubscribe();

        Httpclient client=new Httpclient(vertx,cloudhost.get(),cloudport.get(),cloud_api_Path.get(),cloud_Oauth_token_path.get(),ssl);
        HashMap<String,String> header=new HashMap<>();
        header.put(HEADER_SCHEME,schemeid);
        HashMap<String,String> params=new HashMap<>();
        params.put(GET_IDETIFIER,usecase);
        params.put(GET_OPERATION,SUPERMON_OPERATION_STOPRECORDING);
        Future<JsonObject> jsonObjectFuture=client.sendGetOAUTHRequest(cloud_api_Path.get()+SUPERMON_API_PATH,header,credentials,params);
        jsonObjectFuture.setHandler(jsonObjectAsyncResult -> {
            if(jsonObjectAsyncResult.succeeded())
            {
                logger.debug("Response received "+ jsonObjectAsyncResult.result().toString());
                Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
                SuperMonStopResponse superMonStopResponse=gson.fromJson(jsonObjectAsyncResult.result().toString(), SuperMonStopResponse.class);
                if(superMonStopResponse!=null) {
                    logger.debug("Data receibed from use case"+ superMonStopResponse.getData().getInstanceInformation().getUsecaseIdentifier());
                    logger.debug("status "+superMonStopResponse.getStatus()+ " code "+ superMonStopResponse.getResponseCode());
                    if (superMonStopResponse.getStatus().equalsIgnoreCase(SUPERMON_STATUS_SUCESS) && superMonStopResponse.getResponseCode().intValue() == SUPERMON_CODE_SUCESS.intValue()) {
                        logger.debug("Stop done with sucess "+superMonStopResponse.getData().getInstanceInformation().getUsecaseIdentifier());
                        TestUpdateRequest updateRequest = new TestUpdateRequest();
                        updateRequest.description("SupmerMon Dashboard : " + superMonStopResponse.getData().getApplicationUrl());
                        logger.debug("URL to reach dashboard : "+updateRequest.getDescription());
                        try {
                            resultsApi.updateTest(updateRequest, testid);
                            futureresult.complete(superMonStopResponse.getData().getApplicationUrl());
                        } catch (ApiException e) {
                            logger.error("API Exeption "+e.getResponseBody(),e);
                        }

                    } else {
                        logger.error("Error in the stop recording request");
                        futureresult.fail("ERROR");
                    }
                }
                else
                {
                    logger.error("Conversion error");
                    futureresult.fail(new NeoLoadException("Conversion error "));
                }
            }
            else
                futureresult.fail(jsonObjectAsyncResult.cause());

        });

        return futureresult;
    }


    public Completable run(Vertx vertx)
    {
        return Completable.create(singleSubscriber -> {

            logger.info("Getting description from testid "+ testid);
            Httpclient client=new Httpclient(vertx,cloudhost.get(),cloudport.get(),cloud_api_Path.get(),cloud_Oauth_token_path.get(),ssl);
            HashMap<String,String> header=new HashMap<>();
            header.put(HEADER_SCHEME,schemeid);
            HashMap<String,String> params=new HashMap<>();
            params.put(GET_IDETIFIER,usecase);
            params.put(GET_OPERATION,SUPERMON_OPERATION_RUN);
            logger.info("Sending run request "+ testid);
            Future<JsonObject> jsonObjectFuture=client.sendGetOAUTHRequest(cloud_api_Path.get()+SUPERMON_API_PATH,header,credentials,params);
            jsonObjectFuture.setHandler(jsonObjectAsyncResult -> {
                if(jsonObjectAsyncResult.succeeded())
                {
                    logger.debug("Response received "+ jsonObjectAsyncResult.result().toString());
                    Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
                    SuperMonData superMonData=gson.fromJson(jsonObjectAsyncResult.result().toString(), SuperMonData.class);
                    if(superMonData!=null) {
                        superMonData.getData().convertToSuperMonEntry();
                        logger.debug("Data receibed from use case"+ superMonData.getData().getUsecaseIdentifier());
                        logger.debug("status "+superMonData.getStatus()+ " code "+ superMonData.getResponseCode());

                        if (superMonData.getStatus().equalsIgnoreCase(SUPERMON_RUN_STATUS_SUCESS) && superMonData.getResponseCode().intValue() == SUPERMON_CODE_SUCESS.intValue()) {
                            logger.info("Monitoring data collected, sending it nl wb");
                            //-----convert to nl metrics
                            MonitorPostRequest monitorPostRequest = new MonitorPostRequest();
                            List<CustomMonitor> customMonitorList =superMonData.toCustomMonitor(databaseType, databaseName,logger);
                            try {
                                if(customMonitorList.size()>0) {
                                    monitorPostRequest.monitors(customMonitorList);
                                    logger.debug("Monitoring sent to NeoLoad WEB");
                                    resultsApi.postTestMonitors(monitorPostRequest, testid);
                                }
                                else
                                {
                                    logger.debug("Monitoring data list is empty");
                                }
                                singleSubscriber.onCompleted();

                            } catch (ApiException e) {
                                logger.error("API Exception", e);
                                logger.error("Message -> "+e.getResponseBody());
                                singleSubscriber.onError(e);
                            }
                        } else {
                            logger.debug("RUN - Unable to find data");
                            singleSubscriber.onError(new NeoLoadException("RUN - Unable to find data"));
                        }
                    }
                    else
                    {
                        logger.error("Conversion error ");
                        singleSubscriber.onError(new NeoLoadException("Conversion of response failed"));
                    }
                }
                else {
                    logger.error("RUN error", jsonObjectAsyncResult.cause());
                    singleSubscriber.onError( jsonObjectAsyncResult.cause());
                }


            });
        });

    }
    public Future<Boolean> start(Vertx vertx, Scheduler.Worker worker) throws ApiException,JsonSyntaxException {
        Future<Boolean> booleanFuture=Future.future();
        try {
            TestDefinition testDefinition = resultsApi.getTest(testid);
            logger.info("Getting description from testid " + testid);
            if(testDefinition != null) {
                NeoLoadSuperMonDescription description = getSuperMonDescriptionFromTest(testDefinition.getDescription());
                if (description != null) {
                    logger.debug("description retrived " + description.getSchemeID() + " with usecase " + description.getUseCaseIdentifier());
                    this.schemeid = description.getSchemeID();
                    this.usecase = description.getUseCaseIdentifier();
                    this.databaseName = description.getDatabaseName();
                    this.databaseType = description.getDatabaseType();
                    Future<String> stringFuture = startRecording(vertx, description.getSchemeID(), description.getUseCaseIdentifier());
                    stringFuture.setHandler(stringAsyncResult -> {
                        if (stringAsyncResult.succeeded()) {
                            logger.debug("Start recording done "+ stringAsyncResult.result());
                            subscription = worker.schedulePeriodically(() -> this.run(vertx).subscribe(), SHEDULER_START, SHEDULER_PERIOD, TimeUnit.SECONDS);
                            booleanFuture.complete();
                        } else {
                            logger.error("Technical error ",stringAsyncResult.cause());
                            booleanFuture.fail(stringAsyncResult.cause());
                        }
                    });
                } else {
                    logger.info("No description found");
                    booleanFuture.fail("No decription found");

                }
            }
            else
                booleanFuture.fail("Test id not found");
        }
        catch (ApiException e)
        {
            logger.error("API exception ",e);
            booleanFuture.fail(e);
        }
        catch (JsonSyntaxException e)
        {
            logger.error("Conversion issue",e);
            booleanFuture.fail(e);
        }
        catch (NeoLoadException e)
        {
            logger.error("NeoLoad exception ",e);
            booleanFuture.fail(e);
        }
        catch (Exception e)
        {
            logger.error("Technical Error",e);
            booleanFuture.fail(e);
        }
        return booleanFuture;
    }
    private Future<String> startRecording(Vertx vertx,String schemeid,String usecase)
    {
        Future<String> futureresult=Future.future();
        Httpclient client=new Httpclient(vertx,cloudhost.get(),cloudport.get(),cloud_api_Path.get(),cloud_Oauth_token_path.get(),ssl);
        HashMap<String,String> header=new HashMap<>();
        header.put(HEADER_SCHEME,schemeid);
        HashMap<String,String> params=new HashMap<>();
        params.put(GET_IDETIFIER,usecase);
        params.put(GET_OPERATION,SUPERMON_OPERATION_STARTRECORDING);
        logger.debug("Sending Oauth GET request");
        Future<JsonObject> jsonObjectFuture=client.sendGetOAUTHRequest(cloud_api_Path.get()+SUPERMON_API_PATH,header,credentials,params);
        jsonObjectFuture.setHandler(jsonObjectAsyncResult -> {
            if(jsonObjectAsyncResult.succeeded())
            {
                logger.debug("Received response ");
                Gson gson = new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
                logger.debug("Converting response ");
                logger.debug(jsonObjectAsyncResult.result().toString());
                SupermonStartResponse supermonStartResponse=gson.fromJson(jsonObjectAsyncResult.result().toString(), SupermonStartResponse.class);
                if(supermonStartResponse !=null) {
                    logger.debug(supermonStartResponse.getStatus() + " status of the respose " + supermonStartResponse.getResponseCode() + " " + supermonStartResponse.getData().getInstanceInformation().getDatabaseType());
                    if (supermonStartResponse.getStatus().equalsIgnoreCase(SUPERMON_STATUS_SUCESS) && supermonStartResponse.getResponseCode().intValue() == SUPERMON_CODE_SUCESS.intValue()) {

                        databaseType = supermonStartResponse.getData().getInstanceInformation().getDatabaseType();
                        databaseName = supermonStartResponse.getData().getInstanceInformation().getDatabaseName();

                        logger.debug("Foud "+databaseName+" of "+databaseType);
                        futureresult.complete(SUPERMON_STATUS_SUCESS);
                    } else {
                        if (supermonStartResponse.getErrorCode() != null && supermonStartResponse.getErrorMessage() != null)
                            logger.error("Start recording issue error code :" + supermonStartResponse.getErrorCode() + "message ");
                        else
                            logger.error("Start recording issue code "+supermonStartResponse.getResponseCode() +" status "+ supermonStartResponse.getStatus());

                        futureresult.fail(supermonStartResponse.getStatus());
                    }
                }
                else
                {
                    logger.error("Converstion issue");
                    futureresult.fail(new NeoLoadException("Conversion issue"));
                }
            }
            else {
                logger.error("Technical error ",jsonObjectAsyncResult.cause());
                futureresult.fail(jsonObjectAsyncResult.cause());
            }

        });
        return futureresult;
    }
    private String getTestDescription() throws ApiException {
        String description;

        logger.debug("getting the description of the test");
        TestDefinition definition=resultsApi.getTest(testid);

        if(definition!=null)
        {

            if(!definition.getDescription().isEmpty())
            {
                logger.debug("description of the test : "+ definition.getDescription());
                return definition.getDescription();
            }
            else
            {
                logger.info("The description field is empty");
                return null;
            }
        }
        else {
            logger.error("test not found");
            return null;
        }
    }

    private void generateApiUrl()
    {
        if(neoload_API_Url.isPresent())
        {
            if(!neoload_API_Url.get().contains(API_URL_VERSION))
                neoload_API_Url=Optional.of(neoload_API_Url.get()+API_URL_VERSION);
        }
    }
    private void getEnvVariables() throws NeoLoadException {

        logger.debug("retrieve the environement variables for neoload  neoload service ");
        neoload_API_key=System.getenv(SECRET_API_TOKEN);
        if(neoload_API_key==null)
        {
            logger.error("No API key defined");
            throw new NeoLoadException("No API key is defined");
        }
        neoload_API_Url= Optional.ofNullable(System.getenv(SECRET_NL_API_HOST)).filter(o->!o.isEmpty());
        if(!neoload_API_Url.isPresent())
            neoload_API_Url=Optional.of(DEFAULT_NL_SAAS_API_URL);

        neoload_Web_Url=Optional.ofNullable(System.getenv(SECRET_NL_WEB_HOST)).filter(o->!o.isEmpty());
        if(!neoload_Web_Url.isPresent())
            neoload_Web_Url=Optional.of(SECRET_NL_WEB_HOST);

        if(System.getenv(SECRET_SSL)!=null&& !System.getenv(SECRET_SSL).isEmpty())
        {
            ssl=Boolean.parseBoolean(System.getenv(SECRET_SSL));

        }
        else
            ssl=false;

        //----case of a cloud environment----
        cloudhost=Optional.ofNullable(System.getenv(SECRET_CLOUD_HOST)).filter(o->!o.isEmpty());

        if(!cloudhost.isPresent())
            throw new NeoLoadException("The cloud Host environment variable is missing");


        cloudport=Optional.ofNullable(System.getenv(SECRET_CLOUD_PORT)).filter(o->!o.isEmpty());
        if(!cloudport.isPresent())
            cloudport=Optional.of(DEFAULT_CLOUD_PORT);

        clien_id=Optional.ofNullable(System.getenv(SECRET_CLIENTID)).filter(o->!o.isEmpty());
        if(!clien_id.isPresent())
            throw new NeoLoadException("The client_id environment varaible is missing");

        user=Optional.ofNullable(System.getenv(SECRET_USERNAME)).filter(o->!o.isEmpty());
        if(!user.isPresent())
            throw new NeoLoadException("The user environment varaible is missing");

        password=Optional.ofNullable(System.getenv(SECRET_PASSWORD)).filter(o->!o.isEmpty());
        if(!password.isPresent())
            throw new NeoLoadException("The password environment varaible is missing");

        client_secret=Optional.ofNullable(System.getenv(SECRET_CLIENT_SECRET)).filter(o->!o.isEmpty());
        if(!client_secret.isPresent())
            throw new NeoLoadException("The client_secret environment varaible is missing");


        cloud_api_Path=Optional.ofNullable(System.getenv(SECRET_CLOUD_API_PATH)).filter(o->!o.isEmpty());
        if(!cloud_api_Path.isPresent())
        {
            throw new NeoLoadException("The cloud api path environment varaible is missing");

        }

        cloud_Oauth_token_path=Optional.ofNullable(System.getenv(SECRET_CLOUD_OAUT_TOKEN_PATH)).filter(o->!o.isEmpty());
        if(!cloud_Oauth_token_path.isPresent())
        {
            throw new NeoLoadException("The Oauth token path path environment varaible is missing");

        }
    }
}
