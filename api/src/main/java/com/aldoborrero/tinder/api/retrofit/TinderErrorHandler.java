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

package com.aldoborrero.tinder.api.retrofit;

import com.aldoborrero.tinder.api.retrofit.exceptions.NotValidTokenException;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TinderErrorHandler implements ErrorHandler {

    public static enum ERROR {
        UNAUTHORIZED,
        UNKNOWN
    };

    private static final int UNAUTHORIZED = 401;

    @Override
    public Throwable handleError(RetrofitError cause) {
        Response response = cause.getResponse();
        if (response != null && response.getStatus() == UNAUTHORIZED) {
            return new NotValidTokenException(cause);
        }
        return cause;
    }

}
