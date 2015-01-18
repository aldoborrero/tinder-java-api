/*
 * Copyright 2015 Aldo Borrero <aldo@aldoborrero.com>
 * Copyright (C) 2011 Google Inc.
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

import com.google.gson.*;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Adapts values whose at runtime you can not predict or tell GSON to deserialize
 * to a concrete type. For example, consider this kind of JSON:
 * <pre>{@code
 *   {
 *     "status": 200,
 *     "results": [
 *       {
 *         "_id": "12a9feb15",
 *         "name": "Peter",
 *         "surname": "Parker"
 *       }, {
 *         "_id": "1526ebc23",
 *         "name": "Mary",
 *         "surname": "Jane"
 *       }
 *     ]
 *   }
 * }</pre>
 * At this point looking in {@code "result"} key we can find an array of objects whose
 * contents looks like an {@code User} java entity. On the other hand
 * we can find another JSON that looks similar but its content are different:
 * <pre>{@code
 *   {
 *     "status": 200,
 *     "results": [
 *       {
 *         "lat": 34.0522342,
 *         "lon": -118.2436849
 *       }, {
 *         "lat": -37.814107,
 *         "lon": 144.96328,
 *       }
 *     ]
 *   }
 * }</pre>
 * <p>Without additional type information, the serialized JSON is ambiguous. We cannot
 * guarantee that what comes in {@code "results"} array are {@code User} or {@code Location}
 * entities. This class tries to address this problem using {@link java.util.regex.Pattern}
 * expressions to find a unique match that give GSON the clue of what kind of object has
 * to use in order to deserialize the JSON. So for example, we can create a {@code Pattern} that
 * matches an {@code String} of {@code "_id": "123"} and so on. The Java entities for the
 * examples exposed above are:
 * <pre>{@code
 *   interface Result {
 *   }
 *   class User implements Result {
 *     String _id;
 *     String name;
 *     String surname;
 *   }
 *   class Location implements Result {
 *     long lat;
 *     long lon;
 *   }
 * }</pre>
 * <p/>
 * <h3>Registering Types</h3>
 * Create an instance of {@code RuntimeRegexTypeAdapterFactory} by passing the base
 * type to the {@link #of} factory method.
 * <pre>{@code
 *   RuntimeRegexTypeAdapterFactory<Result> resultAdapterFactory
 *       = RuntimeRegexTypeAdapter.of(Shape.class);
 * }</pre>
 * Next register all of your subtypes. Every subtype must be explicitly
 * registered.
 * <pre>{@code
 *   resultAdapterFactory.registerSubtype(User.class, Pattern.compile(Regex));
 *   resultAdapterFactory.registerSubtype(Location.class, Pattern.compile(Regex));
 * }</pre>
 * Finally, register the type adapter factory in your application's GSON builder:
 * <pre>{@code
 *   Gson gson = new GsonBuilder()
 *       .registerTypeAdapterFactory(resultAdapterFactory)
 *       .create();
 * }</pre>
 * Like {@code GsonBuilder}, this API supports chaining: <pre>{@code
 *   RuntimeTypeAdapter<Result> shapeAdapter = RuntimeTypeAdapterFactory.of(Result.class)
 *       .registerSubtype(User.class, Pattern)
 *       .registerSubtype(Location.class, Pattern);
 * }</pre>
 * <h3>Caveats</h3>
 * <p>First of all, the order in which you define your subtypes determines the order
 * of evaluating {@code Regular Expressions}, so consider to create it sufficiently 
 * different that with a given JSON object it can determine what kind is.</p>
 * <p>Second, you should not relay on creating big and complex {@code Regular Expressions}
 * as this tend to cause some known problems like {@code StackOverflowError}.</p>
 * <p>Third, prior to execute the {@code Regular Expression} this implementation will try
 * to convert the JsonObject to a String representation and from there it tries to find a match.
 * So, don't try to parse very large strings, as this will eat your memory.</p>
 * 
 * <h3>Acknowledgments</h3>
 * <p>This code is more or less based in the class {@code RuntimeTypeAdapterFactory} which can
 * be found here in <a href="http://bit.ly/1yvXSCu">http://bit.ly/1yvXSCu</a>.</p>
 *
 * @author Aldo Borrero <aldo@aldoborrero.com>
 */
public class RuntimeRegexTypeAdapterFactory<T> implements TypeAdapterFactory {

    private final Class<?> baseType;
    private final Map<Class<?>, Pattern> subtypeToPattern = new LinkedHashMap<>(); // Yup, not the best objects to form a K,V Map...

    private RuntimeRegexTypeAdapterFactory(Class<?> baseType) {
        if (baseType == null) {
            throw new IllegalArgumentException("BaseType argument must not be null!");
        }
        this.baseType = baseType;
    }

    /**
     * Creates a new runtime type adapter for {@code baseType}.
     */
    public static <T> RuntimeRegexTypeAdapterFactory<T> of(Class<T> baseType) {
        return new RuntimeRegexTypeAdapterFactory<>(baseType);
    }

    /**
     * Registers {@code type} with its corresponding {@code pattern} object.
     *
     * @throws IllegalArgumentException if either {@code type} or {@code pattern} is null.
     * @throws IllegalArgumentException if either {@code type} or {@code pattern}
     *                                  have already been registered on this type adapter.
     */
    public RuntimeRegexTypeAdapterFactory<T> registerSubtype(Class<? extends T> type, Pattern pattern) {
        if (type == null || pattern == null) {
            throw new IllegalArgumentException("Type or Pattern arguments must not be null!");
        }
        if (subtypeToPattern.containsKey(type)) {
            throw new IllegalArgumentException("You have already registered this type " + type + ".");
        }
        subtypeToPattern.put(type, pattern);

        return this;
    }

    @Override
    public <R> TypeAdapter<R> create(Gson gson, TypeToken<R> type) {
        if (type.getRawType() != baseType) {
            return null;
        }

        final Map<Pattern, TypeAdapter<?>> patternToDelegate = new LinkedHashMap<>();
        final Map<Class<?>, TypeAdapter<?>> subtypeToDelegate = new LinkedHashMap<>();

        for (Map.Entry<Class<?>, Pattern> entry : subtypeToPattern.entrySet()) {
            Class<?> clazz = entry.getKey();
            Pattern pattern = entry.getValue();
            TypeAdapter<?> delegate = gson.getDelegateAdapter(this, TypeToken.get(clazz));
            patternToDelegate.put(pattern, delegate);
            subtypeToDelegate.put(clazz, delegate);
        }

        return new DelegateTypeAdapter<>(patternToDelegate, subtypeToDelegate);
    }

    private static class DelegateTypeAdapter<R> extends TypeAdapter<R> {

        private final Map<Pattern, TypeAdapter<?>> patternToDelegate;
        private final Map<Class<?>, TypeAdapter<?>> subtypeToDelegate;

        public DelegateTypeAdapter(Map<Pattern, TypeAdapter<?>> patternToDelegate, Map<Class<?>, TypeAdapter<?>> subtypeToDelegate) {
            this.patternToDelegate = patternToDelegate;
            this.subtypeToDelegate = subtypeToDelegate;
        }

        @Override
        public void write(JsonWriter out, R value) throws IOException {
            @SuppressWarnings("unchecked")
            TypeAdapter<R> delegate = (TypeAdapter<R>) subtypeToDelegate.get(value.getClass());
            if (delegate == null) {
                throw new JsonParseException("Cannot serialize the object to JSON as there are not a registered delegate.");
            }
            delegate.write(out, value);
        }

        @Override
        public R read(JsonReader in) throws IOException {
            JsonElement jsonElement = Streams.parse(in);
            if (!(jsonElement.isJsonObject())) {
                throw new JsonParseException("Cannot deserialize because it was expected to find a JsonObject.");
            }
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String stringObject = jsonObject.toString();

            return getTypeDelegate(stringObject).fromJsonTree(jsonElement);
        }

        @SuppressWarnings("unchecked")
        private TypeAdapter<R> getTypeDelegate(String stringJson) {
            for (Map.Entry<Pattern, TypeAdapter<?>> entry : patternToDelegate.entrySet()) {
                Pattern p = entry.getKey();
                if (p.matcher(stringJson).find()) {
                    return (TypeAdapter<R>) entry.getValue();
                }
            }
            throw new JsonParseException("Cannot deserialize to a concrete subtype as any of " +
                    "the registered patterns didn't matched a string.");
        }

    }

}
