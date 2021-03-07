package de.reitler.application.household;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.reitler.domain.entities.Household;
import de.reitler.domain.services.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;

public class HouseholdHandler {
    @Autowired
    HouseholdService service;
    ObjectMapper jsonMapper = new ObjectMapper();
    HouseholdInputMapper mapper;
    JsonBody body;

    public HouseholdHandler(HouseholdInputMapper mapper){
        //this.service = service;
        this.mapper = mapper;
    }

    public HouseholdDTO createHousehold(String json) throws JsonProcessingException {
        System.out.println("Jsonstring: "+json);
        body =  jsonMapper.readValue(json,JsonBody.class);
        System.out.println("Parsing Result: "+body.getName());
        Household household = new Household(body.getName());
        System.out.println("Household: "+household.getName());
        service.create(household);
        return mapper.getHouseholdDTO(household.getId().toString(), household.getName());
    }


}

class JsonBody{
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }
}
