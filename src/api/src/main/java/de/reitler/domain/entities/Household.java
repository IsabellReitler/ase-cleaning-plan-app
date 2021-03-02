package de.reitler.domain.entities;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Roommate getRoommate(String id){
        return roommates
                .stream()
                .filter(roommate -> roommate.getId() ==id)
                .findFirst()
                .get();
    }

    public void addRoommate(Roommate roommate){
        roommates.add(roommate);
    }

    public void removeRoommate(Roommate roommate){
        roommates.remove(roommate);
    }
}
