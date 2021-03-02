package de.reitler.domain.entities;

import java.util.List;
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

}
