package de.reitler.plugin.loginapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
    @JsonProperty("email")
    public String email;

    @JsonProperty("given_name")
    public String givenName;

    @JsonProperty("family_name")
    public String familyName;

    @JsonProperty ("picture")
    public String picture;
}
