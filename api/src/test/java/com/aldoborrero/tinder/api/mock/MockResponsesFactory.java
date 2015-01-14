package com.aldoborrero.tinder.api.mock;

import com.squareup.okhttp.mockwebserver.MockResponse;

public class MockResponsesFactory {
    
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String JSON = "application/json; charset=utf-8";
    
    public static MockResponse createAuthResponse() {
        MockResponse response = createJsonResponse();
        response.setBody(Resources.loadBuffer(Resources.AUTH));
        return response;
    }
    
    public static MockResponse createJsonResponse() {
        MockResponse response = new MockResponse();
        response.addHeader(CONTENT_TYPE, JSON);
        return response;
    }

}