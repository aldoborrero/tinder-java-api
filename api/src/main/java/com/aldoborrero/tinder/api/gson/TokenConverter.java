package com.aldoborrero.tinder.api.gson;

import com.aldoborrero.tinder.api.entities.Token;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

class TokenConverter implements JsonDeserializer<Token>, JsonSerializer<Token> {

    public static final Type TYPE = new TypeToken<Token>(){}.getType();
    
    @Override
    public Token deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!(json instanceof JsonPrimitive)) {
            throw new JsonParseException("Token should be a string value!");
        }
        
        return Token.create(json.getAsString());
    }

    @Override
    public JsonElement serialize(Token src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getId());
    }
    
}
