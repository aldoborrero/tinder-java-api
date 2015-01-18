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

package com.aldoborrero.tinder.api.entities;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Response<T extends Result> {

    private Status status;
    private Error error;

    private List<T> results;

    public Response() {
    }
    
    public Response(Status status, List<T> results) {
        this.status = status;
        this.results = results;
    }

    public Status getStatus() {
        return status;
    }

    public Error getError() {
        return error;
    }

    @Nullable
    public List<T> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", results=" + results +
                '}';
    }

    public enum Status {

        @SerializedName("200")
        OK,

        @SerializedName("401")
        UNAUTHORIZED,
        
        @SerializedName("500")
        INTERNAL_SERVER_ERROR
    }
    
    public enum Error {
        API_TOKEN_INVALID,
        NONE
    }
    
}
