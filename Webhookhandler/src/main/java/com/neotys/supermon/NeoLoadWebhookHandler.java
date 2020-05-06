package com.neotys.supermon;


import com.neotys.supermon.httpserver.WebHookReceiver;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.util.concurrent.TimeUnit;

public class NeoLoadWebhookHandler {




        private static final int MAX=24;
        public static void main(String[] args) {

            VertxOptions options=new VertxOptions().setMaxWorkerExecuteTime(MAX).setMaxWorkerExecuteTimeUnit(TimeUnit.HOURS).setWarningExceptionTimeUnit(TimeUnit.HOURS).setWarningExceptionTime(1);
            Vertx.vertx(options).deployVerticle(new WebHookReceiver());


        }
    }


