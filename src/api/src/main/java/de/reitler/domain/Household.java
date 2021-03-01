package de.reitler.domain;

import java.util.List;
import java.util.UUID;

public class Household {
    private UUID id;
    private String name;
    private List<Roommate> roommates;

    public Household(String name){
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
