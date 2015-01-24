package com.aldoborrero.tinder.mock;

import com.aldoborrero.tinder.api.Configuration;
import com.aldoborrero.tinder.api.Tinder;
import com.aldoborrero.tinder.api.services.AsyncTinderService;
import com.aldoborrero.tinder.api.services.ObservableTinderService;
import com.aldoborrero.tinder.api.services.SyncTinderService;
import com.aldoborrero.tinder.mock.services.MockAsyncTinderService;
import com.aldoborrero.tinder.mock.services.MockObservableTinderService;
import com.aldoborrero.tinder.mock.services.MockSyncTinderService;

import retrofit.MockRestAdapter;

public class TinderMock extends Tinder {
    
    public static Tinder create(Configuration configuration) {
        return new TinderMock(configuration);
    }

    private TinderMock(Configuration configuration) {
        super(configuration);
    }

    public ObservableTinderService createMockObservableTinderService() {
        return createMockService(ObservableTinderService.class, new MockObservableTinderService());
    }
    
    public AsyncTinderService createMockAsyncTinderService() {
        return createMockService(AsyncTinderService.class, new MockAsyncTinderService());
    }
    
    public SyncTinderService createMockSyncTinderService() {
        return createMockService(SyncTinderService.class, new MockSyncTinderService());
    }

    private <T> T createMockService(Class<T> service, T mockService) {
        return MockRestAdapter.from(getRestAdapter()).create(service, mockService);
    }
    
}
