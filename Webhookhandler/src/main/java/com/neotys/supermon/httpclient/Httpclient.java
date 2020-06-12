package com.neotys.supermon.httpclient;


import com.neotys.supermon.Logger.NeoLoadLogger;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.OAuth2ClientOptions;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.client.predicate.ResponsePredicate;


import java.time.Instant;
import java.util.HashMap;

import java.util.concurrent.atomic.AtomicReference;

import static com.neotys.supermon.conf.Constants.HTTPS;

public class Httpclient {

    private WebClient client;
    private Vertx vertx;
    private NeoLoadLogger logger;
    private String serverport;
    private String serverhost;
    private ApiOauth apiOauth=null;
    private boolean ssl;
    private String cloudpath;
    private String token_path;

    public Httpclient(Vertx vertx,String serverhost,String serverport,String cloudapipath,String token_path,boolean ssl) {
        this.vertx=vertx;
        this.ssl=ssl;
        this.serverhost=serverhost;
        this.serverport=serverport;
        this.token_path=token_path;
        this.cloudpath=cloudapipath;
        client=WebClient.create(vertx,new WebClientOptions().setSsl(ssl).setLogActivity(true));
        logger=new NeoLoadLogger(this.getClass().getName());

    }

    public void setSsl(boolean ssl)
    {

    }

    public String getServerport() {
        return serverport;
    }

    public void setServerport(String serverport) {
        this.serverport = serverport;
    }

    public String getServerhost() {
        return serverhost;
    }

    public void setServerhost(String serverhost) {
        this.serverhost = serverhost;
    }


    private Future<ApiOauth> getCredentials(Credentials creds )
    {
        Future<ApiOauth> apiOauthFuture=Future.future();
        String site;
        if(ssl)
        {
            site=HTTPS+serverhost+":"+serverport+cloudpath;
        }
        else
            site="http://"+serverhost+":"+serverport+cloudpath;

        logger.debug("Credes seetings : "+site +" client id : "+creds.getClient_id() +" secret : "+ creds.getClient_secret() + " user "+ creds.getUser() + " password "+ creds.getPassword() +" oauth path  "+ this.token_path + " scope "+ creds.getScope());
        logger.debug("OAuth url "+site);
        OAuth2ClientOptions credentials = new OAuth2ClientOptions()
                .setClientID(creds.getClient_id())
                .setClientSecret(creds.getClient_secret())
                .setSite(site)
                .setTokenPath(this.token_path);

        OAuth2Auth oauth2 = OAuth2Auth.create(vertx, OAuth2FlowType.PASSWORD, credentials);

        JsonObject tokenConfig = new JsonObject()
                .put("username", creds.getUser())
                .put("password", creds.getPassword())
                .put("scope",creds.getScope());

        oauth2.authenticate(tokenConfig, res -> {
            if (res.failed()) {
                logger.error("OAuth Failed ",res.cause());
                apiOauthFuture.fail(res.cause());
            } else {
                // Get the access token object (the authorization code is given from the previous step).
                User token = res.result();
                logger.info("Auth successful "+ token.principal().toString());
                apiOauthFuture.complete(new ApiOauth(token));
            }

        });
        return apiOauthFuture;
    }

    private boolean isAuthentificated()
    {
        if(apiOauth==null)
            return false;
        else
        {
            long now = Instant.now().toEpochMilli();
            if(now>apiOauth.getExpires())
                return false;
            else
                return true;
        }
    }

    public Future<String> sendJsonObjectStringResult(String uri, HashMap<String,String> headers, JsonObject object)
    {

        Future<String> future=Future.future();
        HttpRequest<Buffer> request = client.post(Integer.parseInt(serverport),serverhost,uri);

        MultiMap header=((HttpRequest) request).headers();
        header.addAll(headers);
        request.putHeaders(header)
                .expect(ResponsePredicate.JSON)
                .expect(ResponsePredicate.status(200,300))
                .sendJson(object,handler->{
                    if(handler.succeeded())
                    {
                        logger.debug("Request sent successfuly - uri :"+uri+" payload :"+object.toString());
                        logger.debug("Received the following response :"+ handler.result().bodyAsString());
                        future.complete(handler.result().bodyAsString());

                    }
                    else
                    {
                        logger.error("Issue to receive response ");
                        if(handler.result()!=null) {
                            logger.error("Response code :" + handler.result().statusCode() + " and response  " + handler.result().bodyAsString());
                            future.fail("Response code :" + handler.result().statusCode() + " and response  " + handler.result().bodyAsString());
                        }
                        else {
                            logger.error("no Response ", handler.cause());
                            future.fail("no Response " + handler.cause().getMessage());

                        }

                    }

                });

        return future;
    }

    public void sendGetRequest(Future<JsonObject> future,String uri, HashMap<String,String> headers,HashMap<String,String> queryParams)
    {

        logger.debug("Sending get request to "+ uri +" servhost "+serverhost +" port "+ serverport  );
        HttpRequest<Buffer> request = client.get(Integer.parseInt(serverport),serverhost,uri);

        MultiMap header=((HttpRequest) request).headers();
        headers.forEach((s, s2) -> {
            logger.debug("adding header "+s+" value "+s2);
            header.add(s,s2);
        });


        if(queryParams!=null)
        {
            logger.debug("adding parameters ");
            queryParams.forEach((s, s2) -> {
                request.addQueryParam(s,s2);
                logger.debug(" parametere "+s+" value "+s2);
            });
        }

        header.forEach(stringStringEntry -> {

            logger.debug("Hader "+ stringStringEntry.getKey()+" value "+ stringStringEntry.getValue());
        });

        request.putHeaders(header)
                .send(handler->{
                    if(handler.succeeded())
                    {
                        if(handler.result().statusCode()>=200 && handler.result().statusCode()<400) {
                            logger.debug("Request sent successfuly - uri :" + uri);
                            logger.debug("Received the following response :" + handler.result().toString());
                            future.complete(handler.result().bodyAsJsonObject());

                        }
                        else
                        {
                            logger.error("Response code :" + handler.result().statusCode() + " and response  " + handler.result().bodyAsString());
                            future.fail("Response code :" + handler.result().statusCode() + " and response  " + handler.result().bodyAsString());

                        }
                    }
                    else
                    {
                        logger.error("Issue to get response ");
                        if(handler.result()!=null) {
                            logger.error("Response code :" + handler.result().statusCode() + " and response  " + handler.result().bodyAsString());
                            future.fail("Response code :" + handler.result().statusCode() + " and response  " + handler.result().bodyAsString());

                        }
                        else {
                            logger.error("no Response ", handler.cause());
                            future.fail("no Response " + handler.cause().getMessage());

                        }

                    }

                });


    }
    public Future<JsonObject> sendGetOAUTHRequest(String uri, HashMap<String,String> headers,Credentials credentials,HashMap<String,String> queryParams)
    {

        Future<JsonObject> future=Future.future();
        if(!isAuthentificated())
        {
            Future<ApiOauth> apiOauthFuture=getCredentials(credentials);
            apiOauthFuture.setHandler(apiOauthAsyncResult -> {
                if(apiOauthAsyncResult.succeeded())
                {
                    apiOauth=apiOauthAsyncResult.result();
                    headers.put("Authorization","Bearer "+apiOauth.getToken());
                    logger.debug("Authentification done - seding request ");
                    headers.forEach((s, s2) -> {
                        logger.debug("header key "+s +" value "+s2);
                    });
                    sendGetRequest(future,uri,headers,queryParams);
                }
                else
                {
                    logger.debug("Authentification failed");
                    future.fail(apiOauthAsyncResult.cause());
                }
            });

        }
        else
        {
            headers.put("Authorization","Bearer "+apiOauth.getToken());
            sendGetRequest(future,uri,headers,queryParams);
        }


        return future;

    }

    public Future<JsonObject> sendJsonObject(String uri, HashMap<String,String> headers, JsonObject object)
    {

        Future<JsonObject> future=Future.future();
        HttpRequest<Buffer> request = client.post(Integer.parseInt(serverport),serverhost,uri);

        MultiMap header=((HttpRequest) request).headers();
        header.addAll(headers);
        request.putHeaders(header)
                .expect(ResponsePredicate.JSON)
                .expect(ResponsePredicate.status(200,300))
                .sendJson(object,handler->{
                    if(handler.succeeded())
                    {
                        logger.debug("Request sent successfuly - uri :"+uri+" payload :"+object.toString());
                        future.complete(handler.result().bodyAsJsonObject());
                        logger.debug("Received the following response :"+ handler.result().toString());
                    }
                    else
                    {
                        logger.error("Issue to get response ");
                        if(handler.result()!=null) {
                            future.fail("Response code :" + handler.result().statusCode() + " and response  " + handler.result().bodyAsString());
                            logger.error("Response code :" + handler.result().statusCode() + " and response  " + handler.result().bodyAsString());
                        }
                        else {
                            future.fail("no Response " + handler.cause().getMessage());
                            logger.error("no Response ", handler.cause());
                        }

                    }

                });

        return future;
    }
}
