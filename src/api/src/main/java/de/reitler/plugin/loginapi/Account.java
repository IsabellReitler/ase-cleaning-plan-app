package de.reitler.plugin.loginapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.reitler.application.login.UserDataInput;

public class Account implements UserDataInput {
    @JsonProperty("email")
    public String email;

    @JsonProperty("given_name")
    public String givenName;

    @JsonProperty("family_name")
    public String familyName;

    @JsonProperty ("picture")
    public String picture;

    @Override
    public String getFirstName() {
        return givenName;
    }

    @Override
    public String getLastName() {
        return familyName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPicture() {
        return picture;
    }
}
