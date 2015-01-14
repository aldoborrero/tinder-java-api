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

package com.aldoborrero.tinder.api;

import com.aldoborrero.tinder.api.utils.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit.Endpoint;

import java.util.Arrays;
import java.util.List;

/** Configuration object to create instances of {@link com.aldoborrero.tinder.api.Tinder}. */
public interface Configuration {

    /**
     * A default {@link Configuration} which mimics those defaults found in original Android client.
     */
    final Configuration DEFAULT = new Configuration() {

        @NotNull
        @Override
        public Endpoint getEndPoint() {
            return new TinderEndpoint();
        }

        @Override
        public List<Header> getExtraHeaders() {
            return TinderDefaultHeaders.get();
        }

    };

    @NotNull
    Endpoint getEndPoint();

    @Nullable
    List<Header> getExtraHeaders();

    class Header {

        public Header(String name, String value) {
            this.name = name;
            this.value = value;
        }

        String name;
        String value;
    }

    class TinderDefaultHeaders {
        private static final Header USER_AGENT_HEADER = new Header("User-Agent", "");
        private static final Header OS_VERSION_HEADER = new Header("os-version", "");
        private static final Header APP_VERSION_HEADER = new Header("app-version", "");
        private static final Header PLATFORM_HEADER = new Header("platform", "");

        public static List<Header> get() {
            return Arrays.asList(USER_AGENT_HEADER, OS_VERSION_HEADER, APP_VERSION_HEADER, PLATFORM_HEADER);
        }

        private TinderDefaultHeaders() {
            throw new AssertionError("No instances of this class are allowed!");
        }
    }

    /**
     * A convenience class to extend when you only want to listen for a subset
     * of all the methods. It implements those methods that are optional, leaving only those which
     * are not optional untouched.
     */
    abstract class SimpleConfiguration implements Configuration {

        @Override
        public List<Header> getExtraHeaders() {
            return null;
        }

    }

    class TinderEndpoint implements Endpoint {

        @Override
        public String getUrl() {
            return "https://api.gotinder.com";
        }

        @Override
        public String getName() {
            return "Production";
        }

    }

    class Validator {

        public static Configuration validate(Configuration configuration) {
            Preconditions.checkNotNull(configuration, "Configuration must not be null!");
            Preconditions.checkNotNull(configuration.getEndPoint(), "Configuration Endpoint must not be null!");
            return configuration;
        }
        
        private Validator() {
            throw new AssertionError("No instances of this class are allowed!");
        }

    }

}
