package de.reitler.app.apiservice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HouseholdRequestBody{
    @SerializedName("name")
    @Expose
    String name;

    public HouseholdRequestBody(String name){
        this.name = name;
    }
}
