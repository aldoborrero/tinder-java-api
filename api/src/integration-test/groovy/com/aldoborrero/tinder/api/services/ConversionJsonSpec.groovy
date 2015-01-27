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

package com.aldoborrero.tinder.api.services

import com.aldoborrero.tinder.api.Tinder
import com.aldoborrero.tinder.api.base.TinderAbstractSpec
import com.aldoborrero.tinder.api.entities.*
import com.aldoborrero.tinder.api.gson.TinderGsonFactory
import com.aldoborrero.tinder.api.interfaces.TinderErrorHandlerListener
import com.aldoborrero.tinder.api.mock.Assertions
import com.aldoborrero.tinder.api.mock.MockResponsesFactory
import com.aldoborrero.tinder.api.mock.ResourcesLoader
import com.aldoborrero.tinder.api.okhttp.interceptors.AuthTokenInterceptor
import com.aldoborrero.tinder.api.retrofit.TinderEndpoint
import com.google.gson.Gson
import com.google.gson.JsonElement
import retrofit.Endpoint
import retrofit.Endpoints
import retrofit.RestAdapter
import spock.lang.Ignore

class ConversionJsonSpec extends TinderAbstractSpec {

    def Gson gson;
    def SyncTinderService tinderService;

    def setup() {
        setupGson()
        setupTinderService()
    }

    def setupGson() {
        gson = TinderGsonFactory.create()
    }

    def setupTinderService() {
        TinderEndpoint endpoint = new TinderEndpoint();
        Endpoint finalEndpoint = Endpoints.newFixedEndpoint(webServer.getUrl("/").toString());
        endpoint.setUrl(finalEndpoint.getUrl());
        endpoint.setName("Production");

        TestAuthTokenInterceptor interceptor = new TestAuthTokenInterceptor();

        tinderService = Tinder.create()
                .setEndpoint(endpoint)
                .setAuthTokenInterceptor(interceptor)
                .setErrorHandlerListener(TinderErrorHandlerListener.NONE)
                .setLog(RestAdapter.Log.NONE)
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .build().createSyncService();
    }

    // TODO: Adapt to use AuthTinderService
    @Ignore
    def "should parse auth information correctly"() {
        /*setup: "fake webserver with auth response"
        webServer.enqueue(MockResponsesFactory.createAuthResponse())

        and: "expected json auth result"
        JsonElement expectedElement = gson.toJsonTree(gson.fromJson(ResourcesLoader.loadAsString(Assertions.AUTH), Auth.class))

        when: "we call tinderservice.auth method"
        Auth auth = tinderService.auth(new AuthData("token", "en"))

        then: "we should obtain a properly auth object"
        auth != null
        expectedElement == gson.toJsonTree(auth)*/
    }

    def "should parse user recommendations correctly"() {
        setup: "fake webserver with recommendations response"
        webServer.enqueue(MockResponsesFactory.createUserRecommendationsResponse())

        and: "expected json recommendations result"
        User expectedUser = gson.fromJson(ResourcesLoader.loadAsString(Assertions.FIRST_USER_RECOMMENDATION), User.class)
        JsonElement expectedElement = gson.toJsonTree(expectedUser)

        when: "we call tinderservice.getUserRecommendations"
        MultipleResponse<User> recommendations = tinderService.getUserRecommendations()

        then: "we should obtain a properly MultipleResponse<User> object"
        recommendations != null
        recommendations.getResults() != null
        recommendations.getResults().size() == 40

        User user = recommendations.getResults().get(0)
        JsonElement userElement = gson.toJsonTree(user)

        userElement == expectedElement
    }

    def "should parse correctly user info"() {
        setup: "fake webserver with user info response"
        webServer.enqueue(MockResponsesFactory.createUserInfoResponse())

        and: "expected json user response"
        User expectedUser = gson.fromJson(ResourcesLoader.loadAsString(Assertions.USER_INFO), User.class)
        JsonElement expectedElement = gson.toJsonTree(expectedUser)

        when: "we call tinderservice.getUserInfo"
        SingleResponse<User> userResponse = tinderService.getUserInfo("whateverid")

        then: "we should obtain a properly SingleResponse<User> object"
        userResponse.getResult() != null
        userResponse.status.equals(Response.Status.OK)

        JsonElement userElement = gson.toJsonTree(userResponse.getResult())

        userElement == expectedElement
    }

    def "should parse correctly popular locations"() {
        setup: "fake webserver with popular locations response"
        webServer.enqueue(MockResponsesFactory.createPopularLocationsResponse())

        and: "expected json popular locations response"
        PopularLocation expectedLocation = gson.fromJson(ResourcesLoader.loadAsString(Assertions.POPULAR_LOCATION), PopularLocation.class)
        JsonElement expectedElement = gson.toJsonTree(expectedLocation)

        when: "we cal tinderservice.getPopularLocations"
        MultipleResponse<PopularLocation> popularResponse = tinderService.getPopularLocations()

        then: "we should obtain a MultipleResponse<PopularLocation> object"
        popularResponse.getResults() != null
        popularResponse.status.equals(Response.Status.OK)

    }

    class TestAuthTokenInterceptor extends AuthTokenInterceptor {
        @Override
        public Auth getAuthObject() {

            Auth auth = new Auth();
            Token token = new Token();
            token.setId("123a");
            auth.setToken(token);

            return auth;
        }

    }

}
