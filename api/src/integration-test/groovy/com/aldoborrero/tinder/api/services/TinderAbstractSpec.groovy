package com.aldoborrero.tinder.api.services

import com.squareup.okhttp.mockwebserver.MockWebServer
import spock.lang.Specification

abstract class TinderAbstractSpec extends Specification {

    protected MockWebServer webServer;

    def setup() {
        setupWebServer();
        setupTinderService()
    }

    def setupWebServer() {
        this.webServer = new MockWebServer()
        try {
            webServer.play()
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong while creating MockWebServer! Nooooo... :'(", e)
        }
    }

    abstract setupTinderService()
    
    def cleanup() {
        this.webServer.shutdown()
    }
    
}