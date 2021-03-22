package de.reitler.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.util.List;

public class Roommate {

    @JsonProperty("id")
    private String id;
    @JsonProperty("display_name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("picture")
    private URI picture;
    @JsonIgnore
    private Household household;
    @JsonProperty("holiday_mode")
    private boolean holidaymode;
    @JsonProperty("links")
    private List<Link> links;

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

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public boolean isHolidaymode() {
        return holidaymode;
    }

    public void setHolidaymode(boolean holidaymode) {
        this.holidaymode = holidaymode;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }
}

class Link{
    @JsonProperty("rel")
    String relation;

    @JsonProperty("href")
    URI href;
}
