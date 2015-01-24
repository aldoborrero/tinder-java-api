package com.aldoborrero.tinder.api.services;

import com.aldoborrero.tinder.api.entities.Auth;
import com.aldoborrero.tinder.api.entities.AuthData;
import retrofit.http.Body;
import retrofit.http.POST;

public interface AuthTinderService {

    @POST("/auth")
    Auth auth(@Body AuthData authData);
    
}
