package de.reitler.plugin.household;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.reitler.application.household.HouseholdDTO;
import org.springframework.hateoas.RepresentationModel;

public class HouseholdDTOImpl extends RepresentationModel<HouseholdDTOImpl> implements HouseholdDTO {
    private String id;
    private String name;

    @JsonCreator
    public HouseholdDTOImpl(@JsonProperty("id") String id, @JsonProperty("name") String name){
        this.id = id;
        this.name = name;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

}
