package com.aldoborrero.tinder.mock.services;

import com.aldoborrero.tinder.api.entities.*;
import com.aldoborrero.tinder.api.services.AsyncTinderService;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Path;

public class MockAsyncTinderService implements AsyncTinderService {

    @Override
    public void auth(@Body AuthData authData, Callback<Auth> cb) {

    }

    @Override
    public void getUserRecommendations(Callback<MultipleResponse<User>> cb) {

    }

    @Override
    public void getUserInfo(@Path("id") String id, Callback<User> cb) {

    }

    @Override
    public void like(@Path("id") String id, Callback<Like> cb) {

    }

    @Override
    public void pass(@Path("id") String id, Callback<Pass> cb) {

    }

    @Override
    public void postMomentsFeed(Callback<Moments> cb) {

    }

    @Override
    public void likeMoment(@Path("id") String id, Callback<LikeMoment> cb) {

    }

    @Override
    public void passMoment(@Path("id") String id, Callback<PassMoment> cb) {

    }

    @Override
    public void getPopularLocations(Callback<MultipleResponse<PopularLocation>> cb) {

    }

    @Override
    public void postUpdates(Callback<Updates> cb) {

    }

    @Override
    public void updateUserInformation(Callback<User> cb) {

    }
}
