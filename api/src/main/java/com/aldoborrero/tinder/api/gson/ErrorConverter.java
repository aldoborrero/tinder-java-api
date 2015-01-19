package com.aldoborrero.tinder.api.gson;

import com.aldoborrero.tinder.api.entities.MultipleResponse;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ErrorConverter implements JsonDeserializer<MultipleResponse.Error> {

    public static final Type TYPE = new TypeToken<MultipleResponse.Error>(){}.getType();
    
    @Override
    public MultipleResponse.Error deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!(json instanceof JsonPrimitive)) {
            throw new JsonParseException("Token should be a string value!");
        }
        
        return ConverterRegex.INVALID_TOKEN_ERROR.matcher(json.getAsString()).find() ? 
                MultipleResponse.Error.API_TOKEN_INVALID :
                MultipleResponse.Error.NONE;
    }
    
}
