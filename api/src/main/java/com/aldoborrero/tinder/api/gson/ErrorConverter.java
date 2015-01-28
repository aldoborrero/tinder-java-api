/*
* Copyright 2015 Aldo Borrero <aldo@aldoborrero.com>
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

import com.aldoborrero.tinder.api.entities.MultipleResponse;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ErrorConverter implements JsonDeserializer<MultipleResponse.Error> {

    public static final Type TYPE = new TypeToken<MultipleResponse.Error>(){}.getType();

    @Override
    public MultipleResponse.Error deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) 
            throws JsonParseException {
        if (!(json instanceof JsonPrimitive)) {
            throw new JsonParseException("Token should be a string value!");
        }

        return ConverterRegex.INVALID_TOKEN_ERROR.matcher(json.getAsString()).find() ?
                MultipleResponse.Error.API_TOKEN_INVALID :
                MultipleResponse.Error.NONE;
    }

}
