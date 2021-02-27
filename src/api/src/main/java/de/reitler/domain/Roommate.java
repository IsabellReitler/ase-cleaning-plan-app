package de.reitler.domain;

import org.aspectj.lang.annotation.RequiredTypes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Roommate {

    private UUID id;  //e.g. h1r1 --> household #1, roommate #1
    private Household household;
    private String firstname;
    private String lastname;
    private String email;
    private String picture;
    private List<Task> tasks;

    public Roommate(String firstname, Optional<String> lastname, String email, Optional<String> picture){
        this.id = UUID.randomUUID();
        this.firstname = firstname;
        this.lastname = lastname.get();
        this.email = email;
        this.picture = picture.get();
        this.household = null;
    }

}
