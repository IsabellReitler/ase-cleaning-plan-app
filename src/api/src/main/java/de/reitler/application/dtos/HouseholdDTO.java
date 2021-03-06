package de.reitler.application.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HouseholdDTO{
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;

    @JsonCreator
    public HouseholdDTO(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
