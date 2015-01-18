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

import com.aldoborrero.tinder.api.entities.Result;
import com.aldoborrero.tinder.api.entities.User;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class TinderGsonFactory {

    public static Gson create() {
        RuntimeRegexTypeAdapterFactory<Result> typeAdapterFactory = RuntimeRegexTypeAdapterFactory.of(Result.class)
                .registerSubtype(User.class, ConverterRegex.USER_ID);

        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .registerTypeAdapter(IsoDateConverter.TYPE, new IsoDateConverter())
                .registerTypeAdapter(TokenConverter.TYPE, new TokenConverter())
                .registerTypeAdapter(ErrorConverter.TYPE, new ErrorConverter())
                .registerTypeAdapterFactory(typeAdapterFactory)
                .create();
    }

    private TinderGsonFactory() {
        throw new AssertionError("No instances of this class are allowed!");
    }
    
}
