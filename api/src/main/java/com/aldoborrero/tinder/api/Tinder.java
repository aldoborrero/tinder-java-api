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

import com.aldoborrero.tinder.api.services.AsyncTinderService;
import com.aldoborrero.tinder.api.services.MockTinderService;
import com.aldoborrero.tinder.api.services.ObservableTinderService;
import com.aldoborrero.tinder.api.services.SyncTinderService;
import com.aldoborrero.tinder.api.utils.TinderGsonFactory;
import com.aldoborrero.tinder.api.utils.TinderOkHttpFactory;

import retrofit.Endpoint;
import retrofit.MockRestAdapter;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class Tinder {

    private static final String AUTH_TOKEN_HEADER = "X-Auth-Token";
    private static final String USER_AGENT_HEADER = "User-Agent";
    private static final String OS_VERSION_HEADER = "os-version";
    private static final String APP_VERSION_HEADER = "app-version";
    private static final String PLATFORM_HEADER = "platform";

    private RestAdapter restAdapter;
    private Endpoint endpoint;
    private Configuration configuration;

    public Tinder(EndpointType endpointType, Configuration configuration) {
        this.endpoint = endpointType.endpoint;
        this.configuration = configuration;
    }

    public Tinder(Configuration configuration) {
        this(EndpointType.PRODUCTION, configuration);
    }

    protected RestAdapter getRestAdapter() {
        if (restAdapter == null) {
            restAdapter = new RestAdapter.Builder()
                    .setEndpoint(endpoint)
                    .setClient(new OkClient(TinderOkHttpFactory.create()))
                    .setConverter(new GsonConverter(TinderGsonFactory.create()))
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override
                        public void intercept(RequestFacade requestFacade) {
                            requestFacade.addHeader(AUTH_TOKEN_HEADER, configuration.getAuthToken());
                            requestFacade.addHeader(USER_AGENT_HEADER, configuration.getUserAgent());
                            requestFacade.addHeader(OS_VERSION_HEADER, configuration.getOsVersion());
                            requestFacade.addHeader(APP_VERSION_HEADER, configuration.getAppVersion());
                            requestFacade.addHeader(PLATFORM_HEADER, configuration.getPlatform());
                        }
                    }).build();
        }

        return restAdapter;
    }

    public ObservableTinderService createObservableTinderService() {
        return getRestAdapter().create(ObservableTinderService.class);
    }

    public AsyncTinderService createAsyncTinderService() {
        return getRestAdapter().create(AsyncTinderService.class);
    }

    public SyncTinderService createSyncTinderService() {
        return getRestAdapter().create(SyncTinderService.class);
    }

    public ObservableTinderService createMockObservableTinderService() {
        MockRestAdapter mockRestAdapter = MockRestAdapter.from(getRestAdapter());
        MockTinderService mockTinderService = new MockTinderService();
        return mockRestAdapter.create(ObservableTinderService.class, mockTinderService);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public enum EndpointType {
        PRODUCTION(new ProductionEndpoint()),
        DEVELOPMENT(new DevelopmentEndpoint());

        private Endpoint endpoint;

        EndpointType(Endpoint endpoint) {
            this.endpoint = endpoint;
        }
    }

    private static class ProductionEndpoint implements Endpoint {

        @Override
        public String getUrl() {
            return "https://api.gotinder.com";
        }

        @Override
        public String getName() {
            return "Production";
        }

    }

    private static class DevelopmentEndpoint implements Endpoint {

        @Override
        public String getUrl() {
            return "https://api.gotinder.dev";
        }

        @Override
        public String getName() {
            return "Development";
        }

    }

}
