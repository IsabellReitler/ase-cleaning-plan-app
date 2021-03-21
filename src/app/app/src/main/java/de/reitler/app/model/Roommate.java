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
    private String household;
    @JsonProperty("holiday_mode")
    private boolean holidaymode;
    @JsonProperty("links")
    private List<Link> links;
}

class Link{
    @JsonProperty("rel")
    String relation;

    @JsonProperty("href")
    URI href;
}
