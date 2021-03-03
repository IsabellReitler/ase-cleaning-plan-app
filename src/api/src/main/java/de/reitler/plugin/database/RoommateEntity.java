package de.reitler.plugin.database;

import javax.persistence.*;

@Entity
@Table(name="roommate")
public class RoommateEntity {

    private String id;
    private String displayName;
    private String email;
    private HouseholdEntity householdID;
    private String picture;

    public RoommateEntity(){

    }

    public RoommateEntity(String id, String displayName, String email, HouseholdEntity householdID){
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.householdID = householdID;
    }

    public RoommateEntity(String id, String displayName, String email, HouseholdEntity householdID, String picture){
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.householdID = householdID;
        this.picture = picture;
    }

    @Id
    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "display_name", nullable = false)
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    public HouseholdEntity getHouseholdID(){
        return householdID;
    }
    public void setHouseholdID(HouseholdEntity householdID){
        this.householdID = householdID;
    }

    @Column(name = "picture", nullable = true)
    public String getPicture(){
        return picture;
    }
    public void setPicture(String picture){
        this.picture = picture;
    }
}

