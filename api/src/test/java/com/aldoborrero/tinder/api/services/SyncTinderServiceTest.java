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

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SyncTinderServiceTest {

    private final SyncTinderService tinderService = new Tinder(Tinder.EndpointType.PRODUCTION, Configuration.ANDROID).createSyncTinderService();

    @Test
    public void shouldGetAuthInformationWhenLogging() {
        Auth auth = tinderService.auth(new AuthData("token", "en"));
        assertNotNull(auth);
    }

}
