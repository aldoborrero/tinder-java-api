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

package com.aldoborrero.tinder.api.okhttp.interceptors;

import com.aldoborrero.tinder.api.entities.Auth;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;

import java.io.IOException;

public abstract class AuthTokenInterceptor implements Interceptor {

    private static final String KEY_TOKEN = "X-Auth-Token";

    public AuthTokenInterceptor() {
        // Empty constructor
    }

    /**
     * Abstract method to get token of user.
     * @return Auth object token
     */
    public abstract Auth getAuthObject();

    @Override
    public Response intercept(Chain chain) throws IOException {

        if (getAuthObject() != null) {
            String token = getAuthObject().getToken().getId();
            return chain.proceed(chain.request().newBuilder().addHeader(KEY_TOKEN, token).build());
        }

        return chain.proceed(chain.request());
    }
    
}
