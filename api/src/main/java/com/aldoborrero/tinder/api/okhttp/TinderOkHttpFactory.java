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

package com.aldoborrero.tinder.api.okhttp;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

public final class TinderOkHttpFactory {

    public static final int CONNECT_TIMEOUT_MILLIS = 15 * 1000; // 15s

    public static final int READ_TIMEOUT_MILLIS = 20 * 1000; // 20s

    public static OkHttpClient create() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        client.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        //client.networkInterceptors().add(new AuthTokenInterceptor());
        return client;
    }

    private TinderOkHttpFactory() {
        throw new AssertionError("No instances of this object are allowed!");
    }
    
}
