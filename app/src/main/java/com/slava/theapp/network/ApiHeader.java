package com.slava.theapp.network;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApiHeader {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private String token;
    private String uuid;

    @Inject
    public ApiHeader() {

    }
}
