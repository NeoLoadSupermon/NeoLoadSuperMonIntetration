package com.neotys.supermon.httpclient;

import io.vertx.ext.auth.User;

import static com.neotys.supermon.conf.Constants.OAUT_EXPIRES;
import static com.neotys.supermon.conf.Constants.OAUT_TOKEN;

public class ApiOauth {
    String token;
    Long expires;

    public ApiOauth(User user)
    {
        if(user!=null)
        {
            token=user.principal().getString(OAUT_TOKEN);
            expires=user.principal().getLong(OAUT_EXPIRES);
        }
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }
}
