package com.aldoborrero.tinder.api.services

import com.aldoborrero.tinder.api.Configuration
import com.aldoborrero.tinder.api.Tinder
import com.aldoborrero.tinder.api.mock.MockResponsesFactory
import com.aldoborrero.tinder.api.retrofit.exceptions.NotValidTokenException
import org.jetbrains.annotations.NotNull
import retrofit.Endpoint
import retrofit.Endpoints

class ErrorHandlingSpec extends TinderAbstractSpec {

    SyncTinderService tinderService;
    
    @Override
    def setupTinderService() {
        tinderService = Tinder.create(new Configuration.BaseConfiguration() {
            @NotNull
            @Override
            public Endpoint getEndPoint() {
                return Endpoints.newFixedEndpoint(webServer.getUrl("/").toString())
            }
        }).createSyncService()
    }

    def "should handle auth token exceptions correctly"() {
        setup: "fake webserver with a forbidden 401 response (token invalid)"
        webServer.enqueue(MockResponsesFactory.createBadAuthTokenResponse())
        
        when: "calling whatever call to the API"
        def user
        try {
            user = tinderService.getUserInfo("whateverid")
        } catch (NotValidTokenException e) {

        }
            
        then: "mmm :/"
        user == null
    }
    
}
