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

import com.aldoborrero.tinder.api.entities.*;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface AsyncTinderService {

    @POST("/auth")
    void auth(@Body AuthData authData, Callback<Auth> cb);

    @GET("/user/recs")
    void getUserRecommendations(Callback<Response<User>> cb);

    @GET("/user/{id}")
    void getUserInfo(@Path("id") String id, Callback<Response<User>> cb);

    @GET("/like/{id}")
    void like(@Path("id") String id, Callback<Like> cb);

    @GET("/pass/{id}")
    void pass(@Path("id") String id, Callback<Pass> cb);

    @POST("/feed/moments")
    void postMomentsFeed(Callback<Moments> cb);

    @POST("/moment/{id}/like")
    void likeMoment(@Path("id") String id, Callback<LikeMoment> cb);

    @POST("/moment/{id}/pass")
    void passMoment(@Path("id") String id, Callback<PassMoment> cb);

    @GET("/location/popular")
    void getPopularLocations(Callback<Response<PopularLocation>> cb);

    @POST("/updates")
    void postUpdates(Callback<Updates> cb);

    @POST("/profile")
    void updateUserInformation(Callback<User> cb);

}
