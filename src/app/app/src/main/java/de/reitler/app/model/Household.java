package de.reitler.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.util.List;

public class Household {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonIgnore
    private List<Task> tasks;

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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

