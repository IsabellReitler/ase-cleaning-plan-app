package de.reitler.application.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.net.URI;
import java.net.URL;
import java.util.Calendar;

public class RoommateDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("holiday_mode")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Calendar holidayMode;

    @JsonCreator
    public RoommateDTO(String id, String displayName, String email, URI picture){
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.picture = picture.toString();
    }

    public RoommateDTO(String id, String displayName, String email, URI picture, Calendar holidayMode){
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.picture = picture.toString();
        this.holidayMode = holidayMode;
    }


    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }

    public Calendar getHolidayMode(){
        return holidayMode;
    }

    public void setHolydayMode(){
        this.holidayMode = holidayMode;
    }
}
