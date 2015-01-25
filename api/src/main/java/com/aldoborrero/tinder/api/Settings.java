package com.aldoborrero.tinder.api;

import com.aldoborrero.tinder.api.model.TinderEndpoint;
import com.aldoborrero.tinder.api.model.TinderLog;
import com.aldoborrero.tinder.api.model.TinderLogLevel;
import com.aldoborrero.tinder.api.okhttp.interceptors.AuthTokenInterceptor;
import com.aldoborrero.tinder.api.retrofit.TinderErrorHandler;
import org.jetbrains.annotations.Nullable;
import retrofit.client.Header;

import java.util.Arrays;
import java.util.List;

public class Settings {

    private AuthTokenInterceptor authTokenInterceptor;
    private TinderErrorHandler errorHandler;
    private TinderEndpoint endpoint;
    private TinderLogLevel logLevel;
    private TinderLog log;

    public Settings() {
    }

    public Settings(AuthTokenInterceptor authTokenInterceptor, TinderErrorHandler errorHandler, TinderEndpoint endpoint, TinderLogLevel logLevel, TinderLog log) {
        this.authTokenInterceptor = authTokenInterceptor;
        this.errorHandler = errorHandler;
        this.endpoint = endpoint;
        this.logLevel = logLevel;
        this.log = log;
    }

    public AuthTokenInterceptor getAuthTokenInterceptor() {
        return authTokenInterceptor;
    }

    public void setAuthTokenInterceptor(AuthTokenInterceptor authTokenInterceptor) {
        this.authTokenInterceptor = authTokenInterceptor;
    }

    public TinderErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(TinderErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public TinderEndpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(TinderEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    public TinderLogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(TinderLogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public TinderLog getLog() {
        return log;
    }

    public void setLog(TinderLog log) {
        this.log = log;
    }

    @Nullable
    public List<Header> getExtraHeaders() {
        return Arrays.asList(
                Headers.USER_AGENT_HEADER,
                Headers.OS_VERSION_HEADER,
                Headers.APP_VERSION_HEADER,
                Headers.PLATFORM_HEADER
        );
    }


    static class Headers {
        public static final Header USER_AGENT_HEADER = new Header("User-Agent", "Tinder Android Version 4.0.3");
        public static final Header OS_VERSION_HEADER = new Header("os-version", "21");
        public static final Header APP_VERSION_HEADER = new Header("app-version", "767");
        public static final Header PLATFORM_HEADER = new Header("platform", "android");

        private Headers() {
            throw new AssertionError("No instances of this class are allowed!");
        }
    }
}
