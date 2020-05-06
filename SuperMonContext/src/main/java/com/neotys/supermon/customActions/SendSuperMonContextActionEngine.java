package com.neotys.supermon.customActions;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.neotys.action.result.ResultFactory;
import com.neotys.ascode.swagger.client.ApiClient;
import com.neotys.ascode.swagger.client.api.ResultsApi;
import com.neotys.ascode.swagger.client.model.TestUpdateRequest;
import com.neotys.extensions.action.ActionParameter;
import com.neotys.extensions.action.engine.ActionEngine;
import com.neotys.extensions.action.engine.Context;
import com.neotys.extensions.action.engine.Logger;
import com.neotys.extensions.action.engine.SampleResult;
import com.neotys.supermon.datamodel.NeoLoadSuperMonDescription;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.neotys.action.argument.Arguments.getArgumentLogString;
import static com.neotys.action.argument.Arguments.parseArguments;

public class SendSuperMonContextActionEngine implements ActionEngine {


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
            parsedArgs = parseArguments(parameters, SendSuperMonOptions.values());
        } catch (final IllegalArgumentException iae) {
            return ResultFactory.newErrorResult(context, STATUS_CODE_INVALID_PARAMETER, "Could not parse arguments: ", iae);
        }


        final String project = parsedArgs.get(SendSuperMonOptions.Project.getName()).get();
        final Optional<String> version = parsedArgs.get(SendSuperMonOptions.Version.getName());
        final Optional<String> revision = parsedArgs.get(SendSuperMonOptions.Revision.getName());
        final Optional<String> testplan = parsedArgs.get(SendSuperMonOptions.TestPlan.getName());
        final Optional<String> environment = parsedArgs.get(SendSuperMonOptions.TestEnvironment.getName());

        final Optional<String> fixVersion = parsedArgs.get((SendSuperMonOptions.FixVersions.getName()));
        final Optional<String> tags = parsedArgs.get((SendSuperMonOptions.Tags.getName()));
        final Optional<String> customField = parsedArgs.get((SendSuperMonOptions.CustomFields.getName()));


        final Logger logger = context.getLogger();
        if (logger.isDebugEnabled()) {
            logger.debug("Executing " + this.getClass().getName() + " with parameters: "
                    + getArgumentLogString(parsedArgs, SendSuperMonOptions.values()));
        }


        try {
            Optional<HashMap<String,String>> customFields;
            Optional<List<String>> optinalListofTags;
            ApiClient client=new ApiClient();
            ResultsApi resultsApi=new ResultsApi(client);
            client.setBasePath(getBasePath(context));
            client.setApiKey(context.getAccountToken());
            Gson gson=new Gson();


            TestUpdateRequest testUpdateRequest =new TestUpdateRequest();

            if(customField.isPresent())
            {

                HashMap<String,String> jsonmap=gson.fromJson(customField.get(), HashMap.class);
                customFields=Optional.of(jsonmap);
            }
            else
               customFields=Optional.absent();

            if(tags.isPresent())
            {
                optinalListofTags=Optional.fromNullable(Arrays.asList(tags.get().split(",")));
            }
            else
                optinalListofTags=Optional.absent();

            NeoLoadSuperMonDescription xrayDescription=new NeoLoadSuperMonDescription(project,version,revision,testplan,environment,customFields,optinalListofTags,fixVersion);
            String description=gson.toJson(xrayDescription);

            testUpdateRequest.description(description);
            resultsApi.updateTest(testUpdateRequest,context.getTestId());
            appendLineToStringBuilder(responseBuilder, description);

        }catch (Exception e) {
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