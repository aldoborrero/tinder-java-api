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
import com.aldoborrero.tinder.api.model.TinderEndpoint
import com.aldoborrero.tinder.api.model.TinderLog
import com.aldoborrero.tinder.api.model.TinderLogLevel
import com.aldoborrero.tinder.api.okhttp.interceptors.AuthTokenInterceptor
import com.aldoborrero.tinder.api.retrofit.TinderErrorHandler
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


        TinderEndpoint endpoint = new TinderEndpoint();
        endpoint.setUrl("http://localhost:65119");
        endpoint.setName("Production");

        TinderErrorHandler errorHandler = new TinderErrorHandler();

        // We need to change the naming. I don't like it.
        def tinderPicasso = Tinder.with()
                            .setEndpoint(endpoint)
                            .setAuthTokenInterceptor(interceptor)
                            .setErrorHandler(errorHandler)
                            .setLog(TinderLog.NONE)
                            .setLogLevel(TinderLogLevel.FULL)
                            .build();

/*        def tinder = Tinder.create(new Configuration.BaseConfiguration() {
            @NotNull
            @Override
            public Endpoint getEndPoint() {
                return Endpoints.newFixedEndpoint(webServer.getUrl("/").toString())
            }

            @Override
            AuthTokenInterceptor getAuthTokenInterceptor() {
                return interceptor
            }
        })*/
        authTinderService = tinderPicasso.createAuthTinderService()
        tinderService = tinderPicasso.createSyncService()
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
