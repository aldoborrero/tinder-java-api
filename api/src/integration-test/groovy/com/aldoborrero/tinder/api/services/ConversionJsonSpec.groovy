package com.aldoborrero.tinder.api.services
import com.aldoborrero.tinder.api.Configuration
import com.aldoborrero.tinder.api.Tinder
import com.aldoborrero.tinder.api.base.TinderAbstractSpec
import com.aldoborrero.tinder.api.entities.*
import com.aldoborrero.tinder.api.gson.TinderGsonFactory
import com.aldoborrero.tinder.api.mock.Assertions
import com.aldoborrero.tinder.api.mock.MockResponsesFactory
import com.aldoborrero.tinder.api.mock.ResourcesLoader
import com.aldoborrero.tinder.api.okhttp.interceptors.AuthTokenInterceptor
import com.google.gson.Gson
import com.google.gson.JsonElement
import org.jetbrains.annotations.NotNull
import retrofit.Endpoint
import retrofit.Endpoints
import spock.lang.Ignore

class ConversionJsonSpec extends TinderAbstractSpec {

    def Gson gson;
    def SyncTinderService tinderService;
    
    def setup() {
        setupGson()
        setupTinderService()
    }

    def setupGson() {
        gson = TinderGsonFactory.create()
    }

    def setupTinderService() {
        tinderService = Tinder.create(new Configuration.BaseConfiguration() {
            @NotNull
            @Override
            public Endpoint getEndPoint() {
                return Endpoints.newFixedEndpoint(webServer.getUrl("/").toString())
            }

            @Override
            AuthTokenInterceptor getAuthTokenInterceptor() {
                return new AuthTokenInterceptor()
            }
        }).createSyncService()
    }

    // TODO: Adapt to use AuthTinderService
    @Ignore
    def "should parse auth information correctly"() {
        /*setup: "fake webserver with auth response"
        webServer.enqueue(MockResponsesFactory.createAuthResponse())
        
        and: "expected json auth result"
        JsonElement expectedElement = gson.toJsonTree(gson.fromJson(ResourcesLoader.loadAsString(Assertions.AUTH), Auth.class))

        when: "we call tinderservice.auth method"
        Auth auth = tinderService.auth(new AuthData("token", "en"))
        
        then: "we should obtain a properly auth object"
        auth != null
        expectedElement == gson.toJsonTree(auth)*/
    }
    
    def "should parse user recommendations correctly"() {
        setup: "fake webserver with recommendations response"
        webServer.enqueue(MockResponsesFactory.createUserRecommendationsResponse())

        and: "expected json recommendations result"
        User expectedUser = gson.fromJson(ResourcesLoader.loadAsString(Assertions.FIRST_USER_RECOMMENDATION), User.class)
        JsonElement expectedElement = gson.toJsonTree(expectedUser)
        
        when: "we call tinderservice.getUserRecommendations"
        MultipleResponse<User> recommendations = tinderService.getUserRecommendations()

        then: "we should obtain a properly MultipleResponse<User> object"
        recommendations != null
        recommendations.getResults() != null
        recommendations.getResults().size() == 40
        
        User user = recommendations.getResults().get(0)
        JsonElement userElement = gson.toJsonTree(user)

        userElement == expectedElement
    }
    
    def "should parse correctly user info"() {
        setup: "fake webserver with user info response"
        webServer.enqueue(MockResponsesFactory.createUserInfoResponse())
        
        and: "expected json user response"
        User expectedUser = gson.fromJson(ResourcesLoader.loadAsString(Assertions.USER_INFO), User.class)
        JsonElement expectedElement = gson.toJsonTree(expectedUser)
        
        when: "we call tinderservice.getUserInfo"
        SingleResponse<User> userResponse = tinderService.getUserInfo("whateverid")
        
        then: "we should obtain a properly SingleResponse<User> object"
        userResponse.getResult() != null
        userResponse.status.equals(Response.Status.OK)

        JsonElement userElement = gson.toJsonTree(userResponse.getResult())
        
        userElement == expectedElement
    }
    
    def "should parse correctly popular locations"() {
        setup: "fake webserver with popular locations response"
        webServer.enqueue(MockResponsesFactory.createPopularLocationsResponse())
        
        and: "expected json popular locations response"
        PopularLocation expectedLocation = gson.fromJson(ResourcesLoader.loadAsString(Assertions.POPULAR_LOCATION), PopularLocation.class)
        JsonElement expectedElement = gson.toJsonTree(expectedLocation)
        
        when: "we cal tinderservice.getPopularLocations"
        MultipleResponse<PopularLocation> popularResponse = tinderService.getPopularLocations()
        
        then: "we should obtain a MultipleResponse<PopularLocation> object"
        popularResponse.getResults() != null
        popularResponse.status.equals(Response.Status.OK)
        
    }
    
    def cleanup() {
        webServer.shutdown()
    }

}
