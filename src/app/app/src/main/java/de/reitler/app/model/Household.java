package de.reitler.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.util.List;

public class Household {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("_links")
    private SelfLinks links;

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

    public SelfLinks getLinks() {
        return links;
    }

    public void setLinks(SelfLinks links) {
        this.links = links;
    }
}


class SelfLinks{
    @JsonProperty("self")
    HRef self;
}

class HRef{
    @JsonProperty("href")
    URI href;
}

