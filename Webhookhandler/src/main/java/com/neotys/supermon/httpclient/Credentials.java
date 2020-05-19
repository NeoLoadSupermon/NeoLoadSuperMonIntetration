package com.neotys.supermon.httpclient;

public class   Credentials {
    String user;
    String password;
    String client_id;
    String client_secret;
    String scope;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Credentials(String user, String password, String client_id, String client_secret, String scope) {
        this.user = user;
        this.password = password;
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.scope = scope;
    }
}
