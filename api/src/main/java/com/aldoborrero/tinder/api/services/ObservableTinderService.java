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

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface ObservableTinderService {

    @POST("/auth")
    Observable<Auth> auth(@Body AuthData authData);

    @GET("/user/recs")
    Observable<Recommendations> getUserRecommendations();

    @GET("/user/{id}")
    Observable<User> getUserInfo(@Path("id") String id);

    @GET("/like/{id}")
    Observable<Like> like(@Path("id") String id);

    @GET("/pass/{id}")
    Observable<Pass> pass(@Path("id") String id);

    @POST("/feed/moments")
    Observable<Moments> postMomentsFeed();

    @POST("/moment/{id}/like")
    Observable<LikeMoment> likeMoment(@Path("id") String id);

    @POST("/moment/{id}/pass")
    Observable<PassMoment> passMoment(@Path("id") String id);

    @GET("/location/popular")
    Observable<PopularLocations> getPopularLocations();

    @POST("/updates")
    Observable<Updates> postUpdates();

    @POST("/profile")
    Observable<User> updateUserInformation();

}
