package de.reitler.plugin.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "household")
public class HouseholdEntity {
    private String id;
    private String name;

    public HouseholdEntity(){}

    public HouseholdEntity(String id, String name){
        this.id = id;
        this.name = name;
    }

    @Id
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}