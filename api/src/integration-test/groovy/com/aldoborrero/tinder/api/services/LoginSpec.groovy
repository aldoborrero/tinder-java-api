package com.aldoborrero.tinder.api.services

import com.aldoborrero.tinder.api.Tinder
import com.aldoborrero.tinder.api.base.TinderAbstractSpec
import com.aldoborrero.tinder.api.entities.Auth
import com.aldoborrero.tinder.api.entities.AuthData
import com.aldoborrero.tinder.api.entities.SingleResponse
import com.aldoborrero.tinder.api.entities.Token
import com.aldoborrero.tinder.api.entities.User
import com.aldoborrero.tinder.api.interfaces.TinderErrorHandlerListener
import com.aldoborrero.tinder.api.mock.MockResponsesFactory
import com.aldoborrero.tinder.api.model.TinderEndpoint
import com.aldoborrero.tinder.api.okhttp.interceptors.AuthTokenInterceptor
import com.aldoborrero.tinder.api.retrofit.TinderErrorHandler
import retrofit.Endpoint
import retrofit.Endpoints
import retrofit.RestAdapter

class LoginSpec extends TinderAbstractSpec {
    
    AuthTinderService authTinderService;
    SyncTinderService tinderService;
    
    AuthTokenInterceptor interceptor

    @Override
    def setupTinderService() {
        interceptor = Spy(TestAuthTokenInterceptor)


        TinderEndpoint endpoint = new TinderEndpoint();
        Endpoint finalEndpoint = Endpoints.newFixedEndpoint(webServer.getUrl("/").toString());
        endpoint.setUrl(finalEndpoint.getUrl());
        endpoint.setName("Production");


        TinderErrorHandlerListener errorHandlerListener = new TinderErrorHandlerListener() {
            @Override
            void onError(TinderErrorHandler.ERROR typeError) {

                switch (typeError) {
                    case TinderErrorHandler.ERROR.UNAUTHORIZED:
                        break;
                    case TinderErrorHandler.ERROR.UNKNOWN:
                        break;
                }
            }
        };

        def tinder = Tinder.create()
                            .setEndpoint(endpoint)
                            .setAuthTokenInterceptor(interceptor)
                            .setErrorHandlerListener(errorHandlerListener)
                            .setLog(RestAdapter.Log.NONE)
                            .setLogLevel(RestAdapter.LogLevel.NONE)
                            .build();

        authTinderService = tinder.createAuthTinderService()
        tinderService = tinder.createSyncService()
    }

    def "should login correctly" () {

        setup: "Create webserver fake auth response"
        webServer.enqueue(MockResponsesFactory.createAuthResponse())

        when: "We call to tinder.auth"
        AuthData authData = new AuthData("123", "es")
        Auth auth = authTinderService.auth(authData)

        then: "The auth object not must be null and we need to have the toke of user"
        auth != null
        !auth.getToken().getId().empty
    }

    def "should login correctly and set the auth token in the headers"() {
        setup: "create webserver fake auth response"
        webServer.enqueue(MockResponsesFactory.createAuthResponse())
        
        and: "create a fake user info response"
        webServer.enqueue(MockResponsesFactory.createUserInfoResponse())
        
        when: "we call tinder.auth and then we call tinder.getuserinfo"
        AuthData authData = new AuthData("123awas", "en")
        Auth auth = authTinderService.auth(authData)
        webServer.takeRequest()

        SingleResponse<User> response = tinderService.getUserInfo("fakeId")
        
        then: "auth interceptor has must be called once"
        1 * interceptor.intercept(_)
        String authHeader = webServer.takeRequest().getHeader("X-Auth-Token")
        authHeader != null && authHeader.equals("123a")
    }

    class TestAuthTokenInterceptor extends AuthTokenInterceptor {
        @Override
        Auth getAuthObject() {

            Auth auth = new Auth();
            Token token = new Token();
            token.setId("123a")
            auth.setToken(token);

            return auth;
        }
        
    }
    
}
