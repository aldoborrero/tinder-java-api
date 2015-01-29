/*
* Copyright 2015 Aldo Borrero <aldo@aldoborrero.com>
* Copyright 2015 Jose Luis Franconetti <joseluis.franconetti@gmail.com>
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

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
    public Token deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) 
            throws JsonParseException {
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
