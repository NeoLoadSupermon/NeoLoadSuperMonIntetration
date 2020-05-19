package com.neotys.supermon;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.oauth2.AccessToken;
import io.vertx.ext.auth.oauth2.OAuth2Auth;
import io.vertx.ext.auth.oauth2.OAuth2ClientOptions;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class OauthTesting {

    Vertx vertx;

    @Before
    public void before()   {
        vertx = Vertx.vertx();

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
        CountDownLatch countDownLatch=new CountDownLatch(1);
        OAuth2ClientOptions credentials = new OAuth2ClientOptions()
                .setClientID("performanceDashboardClientId")
                .setClientSecret("ljknsqy9tp6123")
                .setSite("http://3.1.221.225:8110/supermon-dashboard")
                .setTokenPath("/oauth/token");


// Initialize the OAuth2 Library
        OAuth2Auth oauth2 = OAuth2Auth.create(vertx, OAuth2FlowType.PASSWORD, credentials);

        JsonObject tokenConfig = new JsonObject()
                .put("username", "admin@system.com")
                .put("password", "123456")
                .put("scope","read");
// Callbacks
// Save the access token

        oauth2.authenticate(tokenConfig, res -> {
            if (res.failed()) {
                System.err.println("Access Token Error: " + res.cause().getMessage());
            } else {
                // Get the access token object (the authorization code is given from the previous step).
                User token = res.result();
                String result = token .principal().getString("access_token");
                Long expire=token.principal().getLong("expires_at");
                System.out.println(token.principal().toString());
                System.out.println("tokent to use "+ result+ "expires at "+expire.toString());
            }
            countDownLatch.countDown();
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
