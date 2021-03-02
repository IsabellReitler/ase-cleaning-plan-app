package de.reitler.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Roommate {

    private String id;  
    private String displayname;
    private String email;
    private String picture;
    private List<Task> tasks;

    public Roommate(String id, String displayname, String email, String picture){
        this.id = id;
        this.displayname = displayname;
        this.email = email;
        this.picture = picture;
    }

}
