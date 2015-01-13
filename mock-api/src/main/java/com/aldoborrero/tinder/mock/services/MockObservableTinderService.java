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

package com.aldoborrero.tinder.mock.services;

import com.aldoborrero.tinder.api.entities.Auth;
import com.aldoborrero.tinder.api.entities.AuthData;
import com.aldoborrero.tinder.api.entities.Like;
import com.aldoborrero.tinder.api.entities.LikeMoment;
import com.aldoborrero.tinder.api.entities.Moments;
import com.aldoborrero.tinder.api.entities.Pass;
import com.aldoborrero.tinder.api.entities.PassMoment;
import com.aldoborrero.tinder.api.entities.PopularLocations;
import com.aldoborrero.tinder.api.entities.Recommendations;
import com.aldoborrero.tinder.api.entities.Updates;
import com.aldoborrero.tinder.api.entities.User;
import com.aldoborrero.tinder.api.services.ObservableTinderService;

import retrofit.http.Body;
import retrofit.http.Path;
import rx.Observable;

public class MockObservableTinderService implements ObservableTinderService {

    @Override
    public Observable<Auth> auth(@Body AuthData authData) {
        return null;
    }

    @Override
    public Observable<Recommendations> getUserRecommendations() {
        return null;
    }

    @Override
    public Observable<User> getUserInfo(@Path("id") String id) {
        return null;
    }

    @Override
    public Observable<Like> like(@Path("id") String id) {
        return null;
    }

    @Override
    public Observable<Pass> pass(@Path("id") String id) {
        return null;
    }

    @Override
    public Observable<Moments> postMomentsFeed() {
        return null;
    }

    @Override
    public Observable<LikeMoment> likeMoment(@Path("id") String id) {
        return null;
    }

    @Override
    public Observable<PassMoment> passMoment(@Path("id") String id) {
        return null;
    }

    @Override
    public Observable<PopularLocations> getPopularLocations() {
        return null;
    }

    @Override
    public Observable<Updates> postUpdates() {
        return null;
    }

    @Override
    public Observable<User> updateUserInformation() {
        return null;
    }

}
