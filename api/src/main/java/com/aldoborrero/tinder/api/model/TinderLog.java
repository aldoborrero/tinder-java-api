package com.aldoborrero.tinder.api.model;

import retrofit.RestAdapter;

public enum  TinderLog {
    NONE;

    public static RestAdapter.Log parseToRetrofit (TinderLog log) {
        switch (log) {
            case NONE:
                return RestAdapter.Log.NONE;
        }

        return RestAdapter.Log.NONE;
    }
}
