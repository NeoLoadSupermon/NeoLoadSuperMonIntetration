package com.neotys.supermon.httpserver;

import static com.neotys.supermon.conf.Constants.HEALTH_PATH;
import static com.neotys.supermon.conf.Constants.HTTP_PORT;
import static com.neotys.supermon.conf.Constants.SECRET_PORT;
import static com.neotys.supermon.conf.Constants.TESTID_KEY;
import static com.neotys.supermon.conf.Constants.WEBHOOKENDPATH;
import static com.neotys.supermon.conf.Constants.WEBHOOKPATH;

import java.util.HashMap;

import com.google.gson.JsonSyntaxException;
import com.neotys.ascode.swagger.client.ApiException;
import com.neotys.supermon.Logger.NeoLoadLogger;
import com.neotys.supermon.common.NeoLoadException;
import com.neotys.supermon.httpresult.NeoLoadHttpHandler;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import rx.Scheduler;
import rx.schedulers.Schedulers;


public class WebHookReceiver extends AbstractVerticle {
    private HashMap<String, NeoLoadHttpHandler> neoLoadHttpHandlerHashMap;
    private HttpServer server;
    private NeoLoadLogger loadLogger;
    int httpPort;
    private Vertx rxvertx;
    public void start() {
        neoLoadHttpHandlerHashMap=new HashMap<>();
        loadLogger=new NeoLoadLogger(this.getClass().getName());
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        router.post(WEBHOOKPATH).handler(this::postWebHook);
        router.post(WEBHOOKENDPATH).handler(this::postEndWebHook);
        router.get(HEALTH_PATH).handler(this::getHealth);
        String port=System.getenv(SECRET_PORT);
        httpPort = port ==null ? HTTP_PORT : Integer.parseInt(port);
        server = vertx.createHttpServer();
        server.requestHandler(router::accept);
        server.listen(httpPort);
    }
    
    @SuppressWarnings("deprecation")
	private void postEndWebHook(RoutingContext routingContext) {

        if(routingContext.getBody()==null) {
            loadLogger.error("Technical error - there is no payload" );
            routingContext.response().setStatusCode(500).end("Technical error - there is no payload");
            return;
        }
        JsonObject body=routingContext.getBodyAsJson();
        if(body.containsKey(TESTID_KEY)) {
            String testid=body.getString(TESTID_KEY);

            //----is test id currently processed?-----
            if(neoLoadHttpHandlerHashMap.containsKey(testid)) {
                loadLogger.setTestid(testid);
                loadLogger.debug("Received stop Webhook with testid  " + testid);
                try {
                    NeoLoadHttpHandler neoLoadHttpHandler=neoLoadHttpHandlerHashMap.get(testid);
                    Future<String> stringFuture=neoLoadHttpHandler.stopRecording(vertx);
                    stringFuture.setHandler(stringAsyncResult -> {
                        if(stringAsyncResult.succeeded()) {
                            neoLoadHttpHandlerHashMap.remove(testid);
                            loadLogger.info("Stop recording finalized");
                            routingContext.response().setStatusCode(200).end(" Stop recording has been sent ");
                        }
                        else {
                            loadLogger.error("Stop recording has failed ",stringAsyncResult.cause());
                            routingContext.response().setStatusCode(500).end(" Stop recording has Failed ");
                        }
                    });
                }
                catch (Exception e) {
                    loadLogger.error("Technical error " + e.getMessage());
                    e.printStackTrace();
                    routingContext.response().setStatusCode(500).end(e.getMessage());
                }
            }
            else
                loadLogger.info(testid + " is not  in process");

        }
        else
        {
            routingContext.response()
                    .setStatusCode(420)
                    .end("Response time does not have any Schemeid");
        }
    }



    private void getHealth(RoutingContext routingContext) {
        routingContext.response().setStatusCode(200).end("Health Check status OK");
    }

    private void handleWebHookStart(final String testid) throws NeoLoadException, ApiException,JsonSyntaxException {

        if(neoLoadHttpHandlerHashMap.containsKey(testid)) {
           loadLogger.info("This testid Has alreday been processed to start");
        } else {
                Scheduler scheduler = Schedulers.newThread();
                Scheduler.Worker worker = scheduler.createWorker();
                NeoLoadHttpHandler neoLoadHttpHandler=new NeoLoadHttpHandler(testid);
                Future<Boolean> future = neoLoadHttpHandler.start(vertx,worker);
                future.setHandler(asyncResult -> {
                    if(asyncResult.succeeded()) {
                        neoLoadHttpHandlerHashMap.put(testid,neoLoadHttpHandler);
                    }
                });

            }
        }

    private void postWebHook(RoutingContext routingContext) {
        if(routingContext.getBody()==null) {
            loadLogger.error("Technical error - there is no payload" );
            routingContext.response().setStatusCode(500).end("Technical error - there is no payload");
            return;
        }
        JsonObject body = routingContext.getBodyAsJson();
        if(body.containsKey(TESTID_KEY)) {
            String testid=body.getString(TESTID_KEY);

            //----is test id currently processed?-----
            if(!neoLoadHttpHandlerHashMap.containsKey(testid))
            {
                loadLogger.setTestid(testid);
                loadLogger.debug("Received Webhook with testid  " + testid);
                try {
                    handleWebHookStart(testid);
                    routingContext.response().setStatusCode(200).end(" Start recording has been sent ");
                } catch (ApiException e) {
                    {
                        loadLogger.error("ApiException error " + e.getMessage());
                        e.printStackTrace();
                        routingContext.response().setStatusCode(500).end(e.getMessage());
                    }
                } catch (NeoLoadException e) {
                    loadLogger.error("NeoLoadException  " + e.getMessage());
                    e.printStackTrace();
                    routingContext.response().setStatusCode(500).end(e.getMessage());
                }
                catch (Exception e) {
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
