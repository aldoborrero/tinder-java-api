package com.aldoborrero.tinder.api.mock;

import com.squareup.okhttp.mockwebserver.MockResponse;

public class MockResponsesFactory {
    
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String JSON = "application/json; charset=utf-8";
    
    public static MockResponse createAuthResponse() {
        MockResponse response = createJsonResponse();
        response.setBody(ResourcesLoader.loadAsBuffer(FakeResponses.AUTH));
        return response;
    }

    public static MockResponse createUserRecommendationsResponse() {
        MockResponse response = createJsonResponse();
        response.setBody(ResourcesLoader.loadAsBuffer(FakeResponses.USER_RECS));
        return response;
    }

    public static MockResponse createUserInfoResponse() {
        MockResponse response = createJsonResponse();
        response.setBody(ResourcesLoader.loadAsBuffer(FakeResponses.USER_INFO));
        return response;
    }

    public static MockResponse createPopularLocationsResponse() {
        MockResponse response = createJsonResponse();
        response.setBody(ResourcesLoader.loadAsBuffer(FakeResponses.POPULAR_LOCATIONS));
        return response;
    }

    public static MockResponse createJsonResponse() {
        MockResponse response = new MockResponse();
        response.addHeader(CONTENT_TYPE, JSON);
        return response;
    }
}
