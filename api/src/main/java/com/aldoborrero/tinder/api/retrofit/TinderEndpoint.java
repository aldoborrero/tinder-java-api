/*
* Copyright 2015 Aldo Borrero <aldo@aldoborrero.com>
* Copyright 2015 Jose Luis Franconetti <joseluis.franconetti@gmail.com>
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

package com.aldoborrero.tinder.api.model;

import retrofit.Endpoint;

public class TinderEndpoint implements Endpoint {

    public static final TinderEndpoint PRODUCTION = new TinderEndpoint("https://api.gotinder.com", "Production");

    private String url;
    private String name;

    public TinderEndpoint(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public TinderEndpoint() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getName() {
        return name;
    }
}
