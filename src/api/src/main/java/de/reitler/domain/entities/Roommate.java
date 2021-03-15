package de.reitler.domain.entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="roommate")
public class Roommate {

    @Id
    private String id;

    @Column(name = "display_name")
    private String displayname;

    @Column(name = "email")
    private String email;

    @Column(name="picture")
    private String picture;

    @Column(name="holiday_mode")
    private Calendar holidayMode;

    @ManyToOne
    private Household household;

    @OneToMany(mappedBy = "roommate", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Roommate(String id, String displayname, String email, String picture){
        this.id = id;
        this.displayname = displayname;
        this.email = email;
        this.picture = picture;
    }

    public Roommate(){

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Task getTask(UUID id){
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst().get();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public Calendar getHolidayMode() {
        return holidayMode;
    }

    public void setHolidayMode(Calendar holidayMode) {
        this.holidayMode = holidayMode;
    }

}
