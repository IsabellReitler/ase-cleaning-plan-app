package de.reitler.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Roommate {

    private UUID id;  //e.g. h1r1 --> household #1, roommate #1
    private String firstname;
    private String lastname;
    private String email;
    private String picture;
    private List<Task> tasks;

    public Roommate(String firstname, String lastname, String email, String picture){
        this.id = UUID.randomUUID();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.picture = picture;
    }

}
