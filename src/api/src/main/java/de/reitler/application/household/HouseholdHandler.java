package de.reitler.application.household;

import de.reitler.application.roommate.RoommateDTO;
import de.reitler.domain.entities.Household;
import de.reitler.domain.services.HouseholdService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
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
        Household newHousehold = new Household();
        newHousehold.setId(household.getId());
        newHousehold.setName(household.getName());
        Household result = service.update(newHousehold);
        return new HouseholdDTO(result.getId(), result.getName());
    }

    public void deleteHousehold(String householdId){
        service.delete(householdId);
    }

    public List<RoommateDTO> getAllRoommates(String id){
        return service
                .getAllRoommates(id)
                .stream()
                .map(x -> {
                    try {
                        return new RoommateDTO(x.getId(), x.getDisplayname(),x.getEmail(),new URI(x.getPicture()));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                return null;
                })
                .collect(Collectors.toList());
    }

    public List<RoommateDTO> addRoommate(String householdId, String roommateId){
        service.addRoommate(householdId, roommateId);
        return getAllRoommates(householdId);
    }

    public void removeRoommate(String householdId, String roommateId){
        service.removeRoommate(householdId,roommateId);
    }
}
