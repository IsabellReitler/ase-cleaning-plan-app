package de.reitler.app.model;


import android.net.Uri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.URI;


public class Roommate {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("display_name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("picture")
    @Expose
    private URI picture;

    @SerializedName("holiday_mode")
    @Expose
    private boolean holidaymode;

    public Roommate(String id, String displayName, String email, String picture) {
        this.id = id;
        this.name = displayName;
        this.email = email;
        this.picture = URI.create(picture);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public URI getPicture() {
        return picture;
    }

    public void setPicture(URI picture) {
        this.picture = picture;
    }

    public boolean isHolidaymode() {
        return holidaymode;
    }

    public void setHolidaymode(boolean holidaymode) {
        this.holidaymode = holidaymode;
    }

}

