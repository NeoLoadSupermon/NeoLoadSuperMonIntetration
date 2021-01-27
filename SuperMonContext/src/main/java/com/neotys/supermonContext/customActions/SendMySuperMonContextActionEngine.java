package com.neotys.supermonContext.customActions;

import static com.neotys.action.argument.Arguments.getArgumentLogString;
import static com.neotys.action.argument.Arguments.parseArguments;

import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.neotys.action.result.ResultFactory;

import com.neotys.ascode.api.v3.client.ApiClient;
import com.neotys.ascode.api.v3.client.ApiException;
import com.neotys.ascode.api.v3.client.api.ResultsApi;
import com.neotys.ascode.api.v3.client.model.TestResultDefinition;
import com.neotys.ascode.api.v3.client.model.TestResultUpdateRequest;
import com.neotys.extensions.action.ActionParameter;
import com.neotys.extensions.action.engine.ActionEngine;
import com.neotys.extensions.action.engine.Context;
import com.neotys.extensions.action.engine.Logger;
import com.neotys.extensions.action.engine.SampleResult;
import com.neotys.supermonContext.datamodel.NeoLoadSuperMonDescription;

public class SendMySuperMonContextActionEngine implements ActionEngine {


    private static final String STATUS_CODE_INVALID_PARAMETER = "NL-SUPERMON-SENDCONTEXT_ACTION-01";
    private static final String STATUS_CODE_TECHNICAL_ERROR = "NL-SUPERMON-SENDCONTEXT_ACTION-02";
    private static final String STATUS_CODE_BAD_CONTEXT = "NL-SUPERMON-SENDCONTEXT_ACTION-03";
    private static final String NLWEB_VERSION ="v1" ;

    public SampleResult execute(Context context, List<ActionParameter> parameters) {
        final SampleResult sampleResult = new SampleResult();
        final StringBuilder requestBuilder = new StringBuilder();
        final StringBuilder responseBuilder = new StringBuilder();

        final Map<String, Optional<String>> parsedArgs;
        try {
            parsedArgs = parseArguments(parameters, SendMySuperMonOptions.values());
        } catch (final IllegalArgumentException iae) {
            return ResultFactory.newErrorResult(context, STATUS_CODE_INVALID_PARAMETER, "Could not parse arguments: ", iae);
        }


        final String applicationIdentifier = parsedArgs.get(SendMySuperMonOptions.applicationIdentifier.getName()).get();
        Optional<String> useCaseIdentifier = parsedArgs.get(SendMySuperMonOptions.useCaseIdentifier.getName());


        final Logger logger = context.getLogger();
        if (logger.isDebugEnabled()) {
            logger.debug("Executing " + this.getClass().getName() + " with parameters: "
                    + getArgumentLogString(parsedArgs, SendMySuperMonOptions.values()));
        }


        try {

            ApiClient client=new ApiClient();
            ResultsApi resultsApi=new ResultsApi(client);
            client.setBasePath(getBasePath(context));
            client.setApiKey(context.getAccountToken());
            Gson gson=new Gson();



            TestResultUpdateRequest testUpdateRequest =new TestResultUpdateRequest();

            if(!useCaseIdentifier.isPresent())
            {
                TestResultDefinition definition=resultsApi.getTestResult(context.getWorkspaceId(),context.getTestId());
                String testname=definition.getProject()+"_"+definition.getScenario();
                useCaseIdentifier = Optional.of(testname);
            }
            NeoLoadSuperMonDescription superMonDescription=new NeoLoadSuperMonDescription(applicationIdentifier,useCaseIdentifier.get());
            String description=gson.toJson(superMonDescription);

            testUpdateRequest.description(description);
            resultsApi.updateTestResult(testUpdateRequest,context.getWorkspaceId(),context.getTestId());
            appendLineToStringBuilder(responseBuilder, description);

        }
        catch (ApiException e) {
            return ResultFactory.newErrorResult(context, STATUS_CODE_TECHNICAL_ERROR, "Api Exeption  "+e.getResponseBody(), e);
        }
        catch (Exception e) {
            return ResultFactory.newErrorResult(context, STATUS_CODE_TECHNICAL_ERROR, "SuperMon Send Context technical Error ", e);
        }


        sampleResult.setRequestContent(requestBuilder.toString());
        sampleResult.setResponseContent(responseBuilder.toString());


        return sampleResult;
    }

    private String getBasePath(final Context context) {
        final String webPlatformApiUrl = context.getWebPlatformApiUrl();
        final StringBuilder basePathBuilder = new StringBuilder(webPlatformApiUrl);
        if(!webPlatformApiUrl.endsWith("/")) {
            basePathBuilder.append("/");
        }
        basePathBuilder.append(NLWEB_VERSION + "/");
        return basePathBuilder.toString();
    }

    private void appendLineToStringBuilder(final StringBuilder sb, final String line) {
        sb.append(line).append("\n");
    }

    /**
     * This method allows to easily create an error result and log exception.
     */
    private static SampleResult getErrorResult(final Context context, final SampleResult result, final String errorMessage, final Exception exception) {
        result.setError(true);
        result.setStatusCode("NL-SUPERMON_ERROR");
        result.setResponseContent(errorMessage);
        if (exception != null) {
            context.getLogger().error(errorMessage, exception);
        } else {
            context.getLogger().error(errorMessage);
        }
        return result;
    }

    @Override
    public void stopExecute() {
        // TODO add code executed when the test have to stop.
    }
}