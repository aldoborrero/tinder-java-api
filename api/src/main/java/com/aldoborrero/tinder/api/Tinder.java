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

import com.aldoborrero.tinder.api.gson.TinderGsonFactory;
import com.aldoborrero.tinder.api.interfaces.TinderErrorHandlerListener;
import com.aldoborrero.tinder.api.okhttp.TinderOkHttpFactory;
import com.aldoborrero.tinder.api.okhttp.interceptors.AuthTokenInterceptor;
import com.aldoborrero.tinder.api.retrofit.TinderEndpoint;
import com.aldoborrero.tinder.api.retrofit.TinderErrorHandler;
import com.aldoborrero.tinder.api.services.AsyncTinderService;
import com.aldoborrero.tinder.api.services.AuthTinderService;
import com.aldoborrero.tinder.api.services.ObservableTinderService;
import com.aldoborrero.tinder.api.services.SyncTinderService;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Header;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

import java.util.List;

public class Tinder {

    private RestAdapter restAdapter;
    private RestAdapter authRestAdapter;

    private final Settings settings;

    protected Tinder (Settings configuration) {
        settings = configuration;
    }

    public AuthTinderService createAuthTinderService() {
      return getAuthAdapter().create(AuthTinderService.class);
    }

    public ObservableTinderService createObservableService() {
      return getRestAdapter().create(ObservableTinderService.class);
    }

    public AsyncTinderService createAsyncService() {
      return getRestAdapter().create(AsyncTinderService.class);
    }

    public SyncTinderService createSyncService() {
      return getRestAdapter().create(SyncTinderService.class);
    }

    public static Endpoint create () {
      return new TinderBuilder();
    }

    private static class TinderBuilder implements Endpoint, AuthInterceptor, ErrorHandler, Log, LogLevel, Build {

        private TinderEndpoint endpoint;
        private AuthTokenInterceptor authTokenInterceptor;
        private TinderErrorHandlerListener errorHandler;
        private RestAdapter.Log log;
        private RestAdapter.LogLevel logLevel;

        private Settings settings;

        @Override
        public ErrorHandler setAuthTokenInterceptor(AuthTokenInterceptor authTokenInterceptor) {
            this.authTokenInterceptor = authTokenInterceptor;
            return this;
        }

        @Override
        public AuthInterceptor setEndpoint(TinderEndpoint endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        @Override
        public Log setErrorHandlerListener(TinderErrorHandlerListener errorHandler) {
            this.errorHandler = errorHandler;
            return this;
        }

        @Override
        public LogLevel setLog(RestAdapter.Log log) {
            this.log = log;
            return this;
        }

        @Override
        public Build setLogLevel(RestAdapter.LogLevel logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        @Override
        public Tinder build() {
            if (settings == null) {
                settings = new Settings();
            }

            settings.setErrorHandler(errorHandler);
            settings.setAuthTokenInterceptor(authTokenInterceptor);
            settings.setEndpoint(endpoint);
            settings.setLog(log);
            settings.setLogLevel(logLevel);

            return new Tinder(Settings.Validator.validate(settings));
        }
    }

    public interface Endpoint {
        AuthInterceptor setEndpoint (TinderEndpoint endpoint);
    }

    public interface AuthInterceptor {
        ErrorHandler setAuthTokenInterceptor (AuthTokenInterceptor authTokenInterceptor);
    }

    public interface ErrorHandler {
        Log setErrorHandlerListener (TinderErrorHandlerListener errorHandler);
    }

    public interface Log {
        LogLevel setLog (RestAdapter.Log log);
    }

    public interface LogLevel {
        Build setLogLevel(RestAdapter.LogLevel logLevel);
    }

    public interface Build {
        Tinder build ();
    }

    protected RestAdapter getRestAdapter() {
        if (restAdapter == null) {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(settings.getEndpoint())
                    .setClient(new OkClient(TinderOkHttpFactory.create(settings.getAuthTokenInterceptor())))
                    .setConverter(new GsonConverter(TinderGsonFactory.create()))
                    .setRequestInterceptor(
                            new RequestInterceptor() {
                                @Override
                                public void intercept(RequestFacade requestFacade) {
                                    List<Header> headerList = settings.getExtraHeaders();
                                    if (headerList != null) {
                                        for (Header header : headerList) {
                                            requestFacade.addHeader(header.getName(), header.getValue());
                                        }
                                    }
                                }
                            }
                    )
                    .setErrorHandler(new TinderErrorHandler())
                    .setLog(settings.getLog())
                    .setLogLevel(settings.getLogLevel())
                    .build();
        }
        return restAdapter;
    }

    protected RestAdapter getAuthAdapter() {
        if (authRestAdapter == null) {
            authRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(settings.getEndpoint())
                    .setClient(new OkClient(TinderOkHttpFactory.create()))
                    .setConverter(new GsonConverter(TinderGsonFactory.create()))
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade requestFacade) {
                            List<Header> headerList = settings.getExtraHeaders();
                            if (headerList != null) {
                                for (Header header : headerList) {
                                    requestFacade.addHeader(header.getName(), header.getValue());
                                }
                            }
                        }
                    })
                    .setErrorHandler(new TinderErrorHandler())
                    .setLog(settings.getLog())
                    .setLogLevel(settings.getLogLevel())
                    .build();
        }
        return authRestAdapter;
    }

}
