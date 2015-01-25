package com.aldoborrero.tinder.api.services
import com.aldoborrero.tinder.api.Configuration
import com.aldoborrero.tinder.api.Tinder
import com.aldoborrero.tinder.api.base.TinderAbstractSpec
import com.aldoborrero.tinder.api.entities.Auth
import com.aldoborrero.tinder.api.entities.AuthData
import com.aldoborrero.tinder.api.entities.SingleResponse
import com.aldoborrero.tinder.api.entities.Token
import com.aldoborrero.tinder.api.entities.User
import com.aldoborrero.tinder.api.mock.MockResponsesFactory
import com.aldoborrero.tinder.api.okhttp.interceptors.AuthTokenInterceptor
import org.jetbrains.annotations.NotNull
import retrofit.Endpoint
import retrofit.Endpoints

class LoginSpec extends TinderAbstractSpec {
    
    AuthTinderService authTinderService;
    SyncTinderService tinderService;
    
    AuthTokenInterceptor interceptor

    @Override
    def setupTinderService() {
        interceptor = Spy(TestAuthTokenInterceptor)
        
        def tinder = Tinder.create(new Configuration.BaseConfiguration() {
            @NotNull
            @Override
            public Endpoint getEndPoint() {
                return Endpoints.newFixedEndpoint(webServer.getUrl("/").toString())
            }

            @Override
            AuthTokenInterceptor getAuthTokenInterceptor() {
                return interceptor
            }
        })
        authTinderService = tinder.createAuthTinderService()
        tinderService = tinder.createSyncService()
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
