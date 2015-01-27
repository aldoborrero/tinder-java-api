package com.aldoborrero.tinder.api;

import com.aldoborrero.tinder.api.interfaces.TinderErrorHandlerListener;
import com.aldoborrero.tinder.api.model.TinderEndpoint;
import com.aldoborrero.tinder.api.okhttp.interceptors.AuthTokenInterceptor;
import com.aldoborrero.tinder.api.utils.Preconditions;
import org.jetbrains.annotations.Nullable;
import retrofit.RestAdapter;
import retrofit.client.Header;

import java.util.Arrays;
import java.util.List;

public class Settings {

    private AuthTokenInterceptor authTokenInterceptor;
    private TinderErrorHandlerListener errorHandler;
    private TinderEndpoint endpoint;
    private RestAdapter.LogLevel logLevel;
    private RestAdapter.Log log;

    public Settings() {
    }

    public Settings(AuthTokenInterceptor authTokenInterceptor, TinderErrorHandlerListener errorHandler, TinderEndpoint endpoint, RestAdapter.LogLevel logLevel, RestAdapter.Log log) {
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

    public TinderErrorHandlerListener getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(TinderErrorHandlerListener errorHandler) {
        this.errorHandler = errorHandler;
    }

    public TinderEndpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(TinderEndpoint endpoint) {
        this.endpoint = endpoint;
    }

    public RestAdapter.LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(RestAdapter.LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public RestAdapter.Log getLog() {
        return log;
    }

    public void setLog(RestAdapter.Log log) {
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

    static class Validator {

        public static Settings validate(Settings settings) {
            Preconditions.checkNotNull(settings, "Settings must not be null!");
            Preconditions.checkNotNull(settings.getEndpoint(), "Settings Endpoint must not be null!");
            Preconditions.checkNotNull(settings.getLog(), "Settings Log must not be null!");
            Preconditions.checkNotNull(settings.getAuthTokenInterceptor(), "Settings AuthTokenInterceptor must not be null!");
            Preconditions.checkNotNull(settings.getErrorHandler(), "Settings error handle listener must not be null!");
            return settings;
        }

        private Validator() {
            throw new AssertionError("No instances of this class are allowed!");
        }

    }
}
