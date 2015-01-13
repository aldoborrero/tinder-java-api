package com.aldoborrero.tinder.api.mock;

import java.io.IOException;
import java.io.InputStream;

import okio.Buffer;

public class Resources {

    public static final String RESPONSES = "/responses/";
    public static final String AUTH = RESPONSES + "auth.json";
    public static final String LIKE_MATCHED = RESPONSES + "like_matched.json";
    public static final String LIKE_NOT_MATCHED = RESPONSES + "like_not_matched.json";
    public static final String PASS = RESPONSES + "pass.json";
    public static final String MOMENT_LIKE = RESPONSES + "moment_like.json";
    public static final String MOMENT_PASS = RESPONSES + "moment_pass.json";
    public static final String POPULAR_LOCATIONS = RESPONSES + "popular_locations.json";
    public static final String UPDATES_EMPTY = RESPONSES + "updates_empty.json";
    public static final String USER_INFORMATION = RESPONSES + "user_information.json";
    public static final String USER_RECS = RESPONSES + "user_recs.json";

    static InputStream loadResource(String resource) {
        return Resources.class.getResourceAsStream(resource);
    }

    static Buffer loadBuffer(String resource) {
        Buffer b = new Buffer();
        try {
            b.readFrom(loadResource(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
    
}
