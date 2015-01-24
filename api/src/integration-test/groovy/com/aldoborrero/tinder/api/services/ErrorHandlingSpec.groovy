package com.aldoborrero.tinder.api.services

import com.aldoborrero.tinder.api.base.TinderAbstractSpec

class ErrorHandlingSpec extends TinderAbstractSpec {

    SyncTinderService tinderService;
    
    @Override
    def setupTinderService() {
        /*tinderService = Tinder.create(new Configuration.BaseConfiguration() {
            @NotNull
            @Override
            public Endpoint getEndPoint() {
                return Endpoints.newFixedEndpoint(webServer.getUrl("/").toString())
            }

            @Override
            AuthTokenInterceptor getAuthTokenInterceptor() {
                return new AuthTokenInterceptor()
            }
        }).createSyncService()*/
    }

}
