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
import com.aldoborrero.tinder.api.okhttp.TinderOkHttpFactory;
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
    private final Configuration configuration;

    public static Tinder create(Configuration configuration) {
        return new Tinder(configuration);
    }

    protected Tinder(Configuration configuration) {
        this.configuration = Configuration.Validator.validate(configuration);
    }

    protected RestAdapter getRestAdapter() {
        if (restAdapter == null) {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(configuration.getEndPoint())
                    .setClient(new OkClient(TinderOkHttpFactory.create(configuration.getAuthTokenInterceptor())))
                    .setConverter(new GsonConverter(TinderGsonFactory.create()))
                    .setRequestInterceptor(
                            new RequestInterceptor() {
                                @Override
                                public void intercept(RequestFacade requestFacade) {
                                    List<Header> headerList = configuration.getExtraHeaders();
                                    if (headerList != null) {
                                        for (Header header : headerList) {
                                            requestFacade.addHeader(header.getName(), header.getValue());
                                        }
                                    }
                                }
                            }
                    )
                    .setErrorHandler(new TinderErrorHandler())
                    .setLog(configuration.getLog())
                    .setLogLevel(configuration.getLogLevel())
                    .build();
        }
        return restAdapter;
    }

    protected RestAdapter getAuthAdapter() {
        if (authRestAdapter == null) {
            authRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(configuration.getEndPoint())
                    .setClient(new OkClient(TinderOkHttpFactory.create()))
                    .setConverter(new GsonConverter(TinderGsonFactory.create()))
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade requestFacade) {
                            List<Header> headerList = configuration.getExtraHeaders();
                            if (headerList != null) {
                                for (Header header : headerList) {
                                    requestFacade.addHeader(header.getName(), header.getValue());
                                }
                            }
                        }
                    })
                    .setErrorHandler(new TinderErrorHandler())
                    .setLog(configuration.getLog())
                    .setLogLevel(configuration.getLogLevel())
                    .build();
        }
        return authRestAdapter;
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

}
