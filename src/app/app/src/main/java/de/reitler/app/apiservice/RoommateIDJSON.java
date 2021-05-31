package de.reitler.app.apiservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoommateIDJSON {
    @SerializedName("roommate_id")
    @Expose
    String id;

    public RoommateIDJSON(String id) {
        this.id = id;
    }
}
