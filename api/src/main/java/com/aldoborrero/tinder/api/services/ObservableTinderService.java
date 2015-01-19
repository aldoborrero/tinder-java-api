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
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface ObservableTinderService {

    @POST("/auth")
    Observable<Auth> auth(@Body AuthData authData);

    @GET("/user/recs")
    Observable<MultipleResponse<User>> getUserRecommendations();

    @GET("/user/{id}")
    Observable<SingleResponse<User>> getUserInfo(@Path("id") String id);

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
    Observable<MultipleResponse<PopularLocation>> getPopularLocations();

    @POST("/updates")
    Observable<Updates> ping(@Body Update update);

    @POST("/profile")
    Observable<User> updateUserInformation();

}
