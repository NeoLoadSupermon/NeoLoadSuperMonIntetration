package com.neotys.supermon.conf;

public class Constants {

    public static final String HEALTH_PATH="/health";
    public static final String WEBHOOKPATH="/webhookstart";
    public static final String WEBHOOKENDPATH="/webhookend";
    public static final String DEFAULT_NL_SAAS_API_URL="neoload-api.saas.neotys.com";
    public static final String DEFAULT_NL_WEB_API_URL="neoload.saas.neotys.com";
    public static final String API_URL_VERSION="/v3";
    public static final String TESTID_KEY="testid";
    public static final String WORKSPACE_KEY="workspaceid";
    public static final String SECRET_API_TOKEN="NL_API_TOKEN";
    public static final String SECRET_NL_WEB_HOST="NL_WEB_HOST";
    public static final String SECRET_SSL="ssl";
    public static final String SECRET_NL_API_HOST="NL_API_HOST";
    public static final String SECRET_PORT="PORT";
    public static String LOGING_LEVEL_KEY="logging-level";
    public static int HTTP_PORT=8080;

    public static final String SUPERMON="SuperMon";
    public static final String DATABASE="Database";

    public static final int SHEDULER_START=1;
    public static final int SHEDULER_PERIOD=5;

    public static final String SECRET_CLOUD_PORT="mySuperMonPort";
    public static final String SECRET_CLOUD_HOST="mySuperMonAPIHostname";
    public static final String SECRET_CLOUD_API_PATH="mySuperMonAPIPath";
    public static final String SECRET_CLOUD_WEBHOST="mySuperMonWebHostname";
    public static final String SECRET_USERNAME="user";
    public static final String SECRET_PASSWORD="password";
    public static final String SECRET_CLIENTID="client_id";
    public static final String SECRET_CLIENT_SECRET="client_secret";
    public static final String DEFAULT_CLOUD_PORT="443";

    public static final String DEFAULT_MANAGED_PORT="80";
    public static final String NEOLOAD="neoload";

    public static final String MYSUPERMON_STATUS_SUCESS="SUCCESS";
    public static final String MYSUPERMON_RUN_STATUS_SUCESS="200 OK";
    public static final Integer MYSUPERMON_CODE_SUCESS=new Integer(200);

    public static final String SECRET_CLOUD_OAUT_TOKEN_PATH="SUPERMON_OAUTH_PATH";

    public static final String HTTPS="https://";
    public static final String NEOLAOD_WEB_URL="/#!result/";
    public static final String NEOLAOD_WEB_LASTPART_URL="/overview";

    //-----url path to interact with SUPERMON----$
    public static final String MYSUPERMON_API_PATH="/devaten/data/operation";
    public static final String MYSUPERMON_OPERATION_STARTRECORDING="start";
    public static final String MYSUPERMON_OPERATION_STOPRECORDING="stop";
    public static final String MYSUPERMON_OPERATION_RUN="run";
    public static final String GET_OPERATION="action";
    public static final String GET_IDETIFIER="usecaseIdentifier";
    public static final String HEADER_SCHEME="applicationIdentifier";

    public static final String OAUT_TOKEN="access_token";
    public static final String OAUT_EXPIRES="expires_at";

}
