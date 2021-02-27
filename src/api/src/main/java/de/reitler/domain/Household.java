package de.reitler.domain;

import java.util.UUID;

public class Household {
    private UUID id;
    private String name;

    public Household(String name){
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
