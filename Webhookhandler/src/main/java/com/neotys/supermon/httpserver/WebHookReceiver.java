package com.neotys.supermon.httpserver;

import com.google.gson.JsonSyntaxException;

import com.neotys.supermon.Logger.NeoLoadLogger;
import com.neotys.supermon.httpresult.NeoLoadHttpHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.HashMap;

import static com.neotys.supermon.conf.Constants.*;


public class WebHookReceiver extends AbstractVerticle {
    private HttpServer server;
    private NeoLoadLogger loadLogger;
    int httpPort;
    private Vertx rxvertx;
    private HashMap<String,String> testidStringStringHashMap;
    public void start() {
        testidStringStringHashMap=new HashMap<>();
        loadLogger=new NeoLoadLogger(this.getClass().getName());
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.post(WEBHOOKPATH).handler(this::postWebHook);
        router.post(WEBHOOKENDPATH).handler(this::postEndWebHook);
        router.get(HEALTH_PATH).handler(this::getHealth);
        String port=System.getenv(SECRET_PORT);
        if(port==null)
        {
            httpPort=HTTP_PORT;
        }
        else
        {
            httpPort=Integer.parseInt(port);
        }
        server = vertx.createHttpServer();

        server.requestHandler(router::accept);
        server.listen(httpPort);
    }

    private void postEndWebHook(RoutingContext routingContext) {
    }

    private void getHealth(RoutingContext routingContext) {
        routingContext.response().setStatusCode(200).end("Health Check status OK");
    }

    private void postWebHook(RoutingContext routingContext) {
        if(routingContext.getBody()==null) {
            loadLogger.error("Technical error - there is no payload" );
            routingContext.response().setStatusCode(500).end("Technical error - there is no payload");
            return;
        }
        JsonObject body=routingContext.getBodyAsJson();
        if(body.containsKey(TESTID_KEY))
        {
            String testid=body.getString(TESTID_KEY);
            String maxVu=body.getString(MAX_VU_KEY);
            String urlpngoverview=body.getString(OVERVIEW_PICTURE_KEY);
            //----is test id currently processed?-----
            if(!testidStringStringHashMap.containsKey(testid))
            {
                loadLogger.setTestid(testid);
                loadLogger.debug("Received Webhook with testid  " + testid);
                testidStringStringHashMap.put(testid,testid);
                try {
                    NeoLoadHttpHandler httpHandler = new NeoLoadHttpHandler(testid, maxVu, urlpngoverview);
                    //Future<Boolean> booleanFuture = httpHandler.sendResult(this.vertx);
                    /*booleanFuture.setHandler(booleanAsyncResult -> {
                        if (booleanAsyncResult.succeeded()) {
                            routingContext.response().setStatusCode(200)
                                    .end("Sending request to supermon");
                            testidStringStringHashMap.remove(testid);
                        } else {
                            routingContext.response().setStatusCode(500)
                                    .end("Issue to reach supermon");
                        }
                    });*/

                } catch (JsonSyntaxException e) {
                    loadLogger.error("JsonSyntaxException error " + e.getMessage());
                    e.printStackTrace();
                    routingContext.response().setStatusCode(500).end(e.getMessage());
                } catch (Exception e) {
                    loadLogger.error("Technical error " + e.getMessage());
                    e.printStackTrace();
                    routingContext.response().setStatusCode(500).end(e.getMessage());
                }
            }
            else
                loadLogger.info(testid + " is already in process");

        }
        else
        {
            routingContext.response()
                    .setStatusCode(420)
                    .end("Response time does not have any Schemeid");
        }
    }
}
