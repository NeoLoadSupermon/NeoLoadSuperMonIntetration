package com.neotys.supermonContext.customActions;

import com.neotys.action.argument.ArgumentValidator;
import com.neotys.action.argument.Option;
import com.neotys.extensions.action.ActionParameter;

import static com.neotys.action.argument.DefaultArgumentValidator.NON_EMPTY;
import static com.neotys.action.argument.Option.AppearsByDefault.False;
import static com.neotys.action.argument.Option.AppearsByDefault.True;
import static com.neotys.action.argument.Option.OptionalRequired.Optional;
import static com.neotys.action.argument.Option.OptionalRequired.Required;
import static com.neotys.extensions.action.ActionParameter.Type.TEXT;

enum SendMySuperMonOptions implements Option {

    applicationIdentifier("applicationIdentifier", Required, True, TEXT,
            "applicationIdentifier of minotored application by mySuperMon",
                    "applicationIdentifier of monitored application by mySuperMon",
          NON_EMPTY),
    databaseType("databaseType", Optional, True, TEXT,
            "Type of Database ( Oracle, MSSSQL...Etc)",
                    "Type of Database ( Oracle, MSSSQL...Etc)",
                NON_EMPTY),
    databaseName("databaseName", Optional, False, TEXT,
            "Name of the database",
                    "Database name",
               NON_EMPTY),
    useCaseIdentifier("useCaseIdentifier", Required, True, TEXT,
            "Use Case identifier to send to mySuperMon",
                    "Use Case identifier to send to mySuperMon ",
                NON_EMPTY);

    private final String name;
    private final OptionalRequired optionalRequired;
    private final AppearsByDefault appearsByDefault;
    private final ActionParameter.Type type;
    private final String defaultValue;
    private final String description;
    private final ArgumentValidator argumentValidator;

    SendMySuperMonOptions(final String name, final OptionalRequired optionalRequired,
                        final AppearsByDefault appearsByDefault,
                        final ActionParameter.Type type, final String defaultValue, final String description,
                        final ArgumentValidator argumentValidator) {
        this.name = name;
        this.optionalRequired = optionalRequired;
        this.appearsByDefault = appearsByDefault;
        this.type = type;
        this.defaultValue = defaultValue;
        this.description = description;
        this.argumentValidator = argumentValidator;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public OptionalRequired getOptionalRequired() {
        return optionalRequired;
    }

    @Override
    public AppearsByDefault getAppearsByDefault() {
        return appearsByDefault;
    }

    @Override
    public ActionParameter.Type getType() {
        return type;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArgumentValidator getArgumentValidator() {
        return argumentValidator;
    }

}