package com.aldoborrero.tinder.api.model;

import retrofit.Endpoint;

public class TinderEndpoint implements Endpoint {


    public static final TinderEndpoint PRODUCTION = new TinderEndpoint("https://api.gotinder.com", "Production");

    private String url;
    private String name;

    public TinderEndpoint(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public TinderEndpoint() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getName() {
        return name;
    }
}
