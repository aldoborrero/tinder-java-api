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
import com.aldoborrero.tinder.api.services.SyncTinderService;

import retrofit.http.Body;
import retrofit.http.Path;

public class MockSyncTinderService implements SyncTinderService {
    
    @Override
    public Auth auth(@Body AuthData authData) {
        return null;
    }

    @Override
    public Recommendations getUserRecommendations() {
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
    public LikeMoment likeMoment(@Path("id") String id) {
        return null;
    }

    @Override
    public PassMoment passMoment(@Path("id") String id) {
        return null;
    }

    @Override
    public PopularLocations getPopularLocations() {
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
