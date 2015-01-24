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

    public static MockResponse createEmptyUpdatesResponse() {
        MockResponse response = createJsonResponse();
        response.setBody(ResourcesLoader.loadAsBuffer(FakeResponses.UPDATES_EMPTY));
        return response;
    }

    public static MockResponse createUpdatesResponse() {
        MockResponse response = createJsonResponse();
        response.setBody(ResourcesLoader.loadAsBuffer(FakeResponses.UPDATES));
        return response;
    }

    public static MockResponse createLikeWithMatchResponse() {
        MockResponse response = createJsonResponse();
        response.setBody(ResourcesLoader.loadAsBuffer(FakeResponses.LIKE_MATCHED));
        return response;
    }

    public static MockResponse createLikeWithoutMatchResponse() {
        MockResponse response = createJsonResponse();
        response.setBody(ResourcesLoader.loadAsBuffer(FakeResponses.LIKE_NOT_MATCHED));
        return response;
    }

    public static MockResponse createPassResponse() {
        MockResponse response = createJsonResponse();
        response.setBody(ResourcesLoader.loadAsBuffer(FakeResponses.PASS));
        return response;
    }

    public static MockResponse createBadAuthTokenResponse() {
        MockResponse response = createJsonResponse();
        response.setBody(ResourcesLoader.loadAsBuffer(FakeResponses.FORBIDDEN));
        response.setResponseCode(401);
        return response;
    }

    public static MockResponse createJsonResponse() {
        MockResponse response = new MockResponse();
        response.addHeader(CONTENT_TYPE, JSON);
        return response;
    }
}
