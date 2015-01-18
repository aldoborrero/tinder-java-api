package com.aldoborrero.tinder.mock.services;

import com.aldoborrero.tinder.api.entities.*;
import com.aldoborrero.tinder.api.services.SyncTinderService;
import retrofit.http.Body;
import retrofit.http.Path;

public class MockSyncTinderService implements SyncTinderService {

    @Override
    public Auth auth(@Body AuthData authData) {
        return null;
    }

    @Override
    public Response<User> getUserRecommendations() {
        return null;
    }

    @Override
    public User getUserInfo(@Path("id") String id) {
        return null;
    }

    @Override
    public Like like(@Path("id") String id) {
        return null;
    }

    @Override
    public Pass pass(@Path("id") String id) {
        return null;
    }

    @Override
    public Moments postMomentsFeed() {
        return null;
    }

    @Override
    public Response<LikeMoment> likeMoment(@Path("id") String id) {
        return null;
    }

    @Override
    public Response<PassMoment> passMoment(@Path("id") String id) {
        return null;
    }

    @Override
    public Response<PopularLocation> getPopularLocations() {
        return null;
    }

    @Override
    public Updates postUpdates() {
        return null;
    }

    @Override
    public User updateUserInformation() {
        return null;
    }
    
}
