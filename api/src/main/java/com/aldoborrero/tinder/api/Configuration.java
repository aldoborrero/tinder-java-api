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

import com.aldoborrero.tinder.api.okhttp.TinderOkHttpFactory;
import com.aldoborrero.tinder.api.retrofit.TinderErrorHandler;
import com.aldoborrero.tinder.api.utils.Preconditions;
import com.squareup.okhttp.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit.Endpoint;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.client.Header;

import java.util.Arrays;
import java.util.List;

/** Configuration object to create instances of {@link com.aldoborrero.tinder.api.Tinder}. */
public interface Configuration {

    /**
     * A default {@link Configuration} which mimics those defaults found in original Android client.
     */
    Configuration DEFAULT = new BaseConfiguration();

    @NotNull
    Endpoint getEndPoint();

    @Nullable
    List<Header> getExtraHeaders();
    
    @NotNull
    ErrorHandler getErrorHandler();
    
    @NotNull
    RestAdapter.Log getLog();
    
    @NotNull
    RestAdapter.LogLevel getLogLevel();
    
    @NotNull
    OkHttpClient getOkClient();

    class Headers {
        public static final Header USER_AGENT_HEADER = new Header("User-Agent", "Tinder Android Version 4.0.3");
        public static final Header OS_VERSION_HEADER = new Header("os-version", "21");
        public static final Header APP_VERSION_HEADER = new Header("app-version", "767");
        public static final Header PLATFORM_HEADER = new Header("platform", "android");

        private Headers() {
            throw new AssertionError("No instances of this class are allowed!");
        }
    }

    /**
     * A convenience class to extend when you only want to listen for a subset
     * of all the methods leaving the rest with the defaults provided here.
     */
    class BaseConfiguration implements Configuration {

        @NotNull
        @Override
        public Endpoint getEndPoint() {
            return new TinderEndpoint();
        }

        @Nullable
        @Override
        public List<Header> getExtraHeaders() {
            return Arrays.asList(
                    Headers.USER_AGENT_HEADER,
                    Headers.OS_VERSION_HEADER,
                    Headers.APP_VERSION_HEADER,
                    Headers.PLATFORM_HEADER
            );
        }

        @NotNull
        @Override
        public ErrorHandler getErrorHandler() {
            return new TinderErrorHandler();
        }

        @NotNull
        @Override
        public RestAdapter.Log getLog() {
            return RestAdapter.Log.NONE;
        }

        @NotNull
        @Override
        public RestAdapter.LogLevel getLogLevel() {
            return RestAdapter.LogLevel.NONE;
        }

        @NotNull
        @Override
        public OkHttpClient getOkClient() {
            return TinderOkHttpFactory.create();
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
            Preconditions.checkNotNull(configuration.getErrorHandler(), "Configuration Error Handler must not be null!");
            Preconditions.checkNotNull(configuration.getLog(), "Configuration Log must not be null!");
            Preconditions.checkNotNull(configuration.getOkClient(), "Configuration OkClient must not be null!");
            return configuration;
        }
        
        private Validator() {
            throw new AssertionError("No instances of this class are allowed!");
        }

    }

}
