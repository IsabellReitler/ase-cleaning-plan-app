package de.reitler.domain.entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "household")
public class Household {
    @Id
    private UUID id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "household",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Roommate> roommates;

    public Household(String name){
        this.id = UUID.randomUUID();
        this.name = name;
    }
    public Household(){

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
