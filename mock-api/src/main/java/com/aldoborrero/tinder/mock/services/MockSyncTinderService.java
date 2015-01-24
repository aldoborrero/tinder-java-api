package com.aldoborrero.tinder.mock.services;

import com.aldoborrero.tinder.api.entities.*;
import com.aldoborrero.tinder.api.services.SyncTinderService;
import retrofit.http.Body;
import retrofit.http.Path;

public class MockSyncTinderService implements SyncTinderService {

    @Override
    public MultipleResponse<User> getUserRecommendations() {
        return null;
    }

    @Override
    public SingleResponse<User> getUserInfo(@Path("id") String id) {
        return null;
    }

    @Override
    public LikeResponse<Match> like(@Path("id") String id) {
        return null;
    }

    @Override
    public PassResponse pass(@Path("id") String id) {
        return null;
    }

    @Override
    public Moments postMomentsFeed() {
        return null;
    }

    @Override
    public MultipleResponse<LikeMoment> likeMoment(@Path("id") String id) {
        return null;
    }

    @Override
    public MultipleResponse<PassMoment> passMoment(@Path("id") String id) {
        return null;
    }

    @Override
    public MultipleResponse<PopularLocation> getPopularLocations() {
        return null;
    }

    @Override
    public Updates ping(@Body Update update) {
        return null;
    }

    @Override
    public User updateUserInformation() {
        return null;
    }
    
}
