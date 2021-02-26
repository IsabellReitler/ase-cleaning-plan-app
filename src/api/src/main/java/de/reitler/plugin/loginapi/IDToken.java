package de.reitler.plugin.loginapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IDToken {
    @JsonProperty("idToken")
    private String token;

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }
}
