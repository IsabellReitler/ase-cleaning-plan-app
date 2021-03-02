package de.reitler.plugin.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.reitler.application.login.UserDataInput;

public class Account implements UserDataInput {

    @JsonProperty("id")
    private String id;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("email")
    private String email;

    @JsonProperty ("picture")
    private String picture;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDisplayName() {
        return displayName;
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
