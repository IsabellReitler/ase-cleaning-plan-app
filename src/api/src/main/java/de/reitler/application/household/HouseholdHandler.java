package de.reitler.application.household;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.reitler.application.roommate.RoommateDTO;
import de.reitler.domain.entities.Household;
import de.reitler.domain.services.HouseholdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class HouseholdHandler {
    @Autowired
    HouseholdService service;


    public HouseholdHandler(){
    }

    public HouseholdDTO createHousehold(HouseholdDTO body) {
        Household household = new Household(body.getName());
        Household output = service.create(household);
        return new HouseholdDTO(output.getId().toString(), output.getName());
    }

    public HouseholdDTO getHousehold(String id){
        Household household = service.getById(id);
        return new HouseholdDTO(household.getId().toString(), household.getName());
    }

    public HouseholdDTO update(HouseholdDTO household){
        Household old = service.update(service.getById(household.getId().toString()));
        return household;
    }

    public List<RoommateDTO> getAllRoommates(String id){
        return service
                .getAllRoommates(id)
                .stream()
                .map(x -> new RoommateDTO(x.getId(), x.getDisplayname(),x.getEmail(), x.getPicture()))
                .collect(Collectors.toList());
    }

    public List<RoommateDTO> addRoommate(String householdId, String roommateId){
        service.addRoommate(householdId, roommateId);
        return getAllRoommates(householdId);
    }
}
