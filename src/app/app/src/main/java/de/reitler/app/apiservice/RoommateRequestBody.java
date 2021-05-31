package de.reitler.app.apiservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoommateRequestBody{
    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("display_name")
    @Expose
    String name;

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("picture")
    @Expose
    String picture;

    public RoommateRequestBody(String id, String name, String email, String picture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
}
