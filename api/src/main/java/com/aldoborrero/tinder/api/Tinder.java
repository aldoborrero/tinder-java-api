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
import com.aldoborrero.tinder.api.services.AsyncTinderService;
import com.aldoborrero.tinder.api.services.ObservableTinderService;
import com.aldoborrero.tinder.api.services.SyncTinderService;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class Tinder {

    private RestAdapter restAdapter;
    private Configuration configuration;

    public static Tinder create() {
        return create(Configuration.DEFAULT);
    }

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
                    .setClient(new OkClient(TinderOkHttpFactory.create()))
                    .setConverter(new GsonConverter(TinderGsonFactory.create()))
                    .setRequestInterceptor(
                            new RequestInterceptor() {
                                @Override
                                public void intercept(RequestFacade requestFacade) {
                                }
                            }
                    )
                    .build();
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

    public Configuration getConfiguration() {
        return configuration;
    }

}
