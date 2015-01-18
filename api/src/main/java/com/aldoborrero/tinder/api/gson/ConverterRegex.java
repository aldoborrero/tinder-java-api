package com.aldoborrero.tinder.api.gson;

import java.util.regex.Pattern;

final class ConverterRegex {
    
    public static final Pattern INVALID_TOKEN_ERROR = Pattern.compile("No session found for user with api_token:");

    public static final Pattern USER_ID = Pattern.compile("\"_id\":\\s?\".*\"");
    
    private ConverterRegex() {
        throw new AssertionError("No instances of this class are allowed!");
    }
    
}
