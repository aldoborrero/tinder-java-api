package com.aldoborrero.tinder.api.model;

import retrofit.RestAdapter;

public enum  TinderLogLevel {
    NONE,
    BASIC,
    HEADERS,
    HEADERS_AND_ARGS,
    FULL;

    public static RestAdapter.LogLevel parseToRetrofit (TinderLogLevel tinderLogLevel) {

        switch (tinderLogLevel) {
            case NONE:
                return RestAdapter.LogLevel.NONE;

            case BASIC:
                return RestAdapter.LogLevel.BASIC;

            case HEADERS:
                return RestAdapter.LogLevel.HEADERS;

            case HEADERS_AND_ARGS:
                return RestAdapter.LogLevel.HEADERS_AND_ARGS;

            case FULL:
                return RestAdapter.LogLevel.FULL;

        }

        return RestAdapter.LogLevel.NONE;
    }

}
