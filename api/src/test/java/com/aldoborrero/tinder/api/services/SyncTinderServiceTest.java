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

package com.aldoborrero.tinder.api.services;

import com.aldoborrero.tinder.api.Configuration;
import com.aldoborrero.tinder.api.Tinder;
import com.aldoborrero.tinder.api.entities.Auth;
import com.aldoborrero.tinder.api.entities.AuthData;
import com.aldoborrero.tinder.api.mock.MockResponsesFactory;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import retrofit.Endpoint;
import retrofit.Endpoints;

import static org.junit.Assert.assertNotNull;

public class SyncTinderServiceTest {

    private MockWebServer webServer;
    private SyncTinderService tinderService;

    @Before
    public void setUp() throws IOException {
        webServer = new MockWebServer();
        webServer.play();
        
        tinderService = Tinder.create(new Configuration.SimpleConfiguration() {
            @NotNull
            @Override
            public Endpoint getEndPoint() {
                return Endpoints.newFixedEndpoint(webServer.getUrl("/").toString());
            }
        }).createSyncTinderService();
    }
    
    @Test
    public void shouldGetAuthInformationWhenLogging() {
        webServer.enqueue(MockResponsesFactory.createAuthResponse());
        
        Auth auth = tinderService.auth(new AuthData("token", "en"));
        assertNotNull(auth);
        assertNotNull(auth.getToken());
        assertNotNull(auth.getGlobals());
        assertNotNull(auth.getUser());
        assertNotNull(auth.getVersions());
        
        System.out.println("Current User: " + auth.getUser().toString());
    }


}
