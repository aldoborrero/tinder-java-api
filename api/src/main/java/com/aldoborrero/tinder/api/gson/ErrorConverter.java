package com.aldoborrero.tinder.api.gson;

import com.aldoborrero.tinder.api.entities.Response;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ErrorConverter implements JsonDeserializer<Response.Error> {

    public static final Type TYPE = new TypeToken<Response.Error>(){}.getType();
    
    @Override
    public Response.Error deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!(json instanceof JsonPrimitive)) {
            throw new JsonParseException("Token should be a string value!");
        }
        
        return ConverterRegex.INVALID_TOKEN_ERROR.matcher(json.getAsString()).find() ? 
                Response.Error.API_TOKEN_INVALID : 
                Response.Error.NONE;
    }
    
}
