package com.popularsafi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultAuth {
    @JsonProperty("access_token")
    private String access_token;

    public ResultAuth(String access_token) {
        super();
        this.access_token = access_token;
    }

    public ResultAuth() {
        super();
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

}
