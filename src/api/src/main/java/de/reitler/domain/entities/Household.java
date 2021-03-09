package de.reitler.domain.entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "household")
public class Household {

    private String id;

    private String name;

    private List<Roommate> roommates;

    public Household(String name){
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
    public Household(){

    }

    @Id
    @Column(name = "id", length = 64)
    public String getId() {
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    @Column(name = "name")
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

    @OneToMany(mappedBy = "household",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Roommate> getRoommates(){
        return roommates;
    }

    public void setRoommates(List<Roommate> roommates){
        this.roommates = roommates;
    }

    public void addRoommate(Roommate roommate){
        roommates.add(roommate);
    }

    public void removeRoommate(Roommate roommate){
        roommates.remove(roommate);
    }
}
