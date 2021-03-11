package de.reitler.plugin.household;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import de.reitler.application.household.HouseholdDTO;
import de.reitler.application.household.HouseholdHandler;
import de.reitler.application.roommate.RoommateDTO;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/household")
public class HouseholdController {

    @Autowired
    HouseholdHandler handler;

    @PostMapping
    public HttpEntity createHousehold(@RequestBody HouseholdDTO household){
            HouseholdDTO dto = (HouseholdDTO) handler.createHousehold(household);
            if(dto != null) {
                dto.add(linkTo(methodOn(HouseholdController.class).getHouseholdById(dto.getId())).withSelfRel());
                return new ResponseEntity<HouseholdDTO>(dto, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
    }

    @PutMapping("/{id}")
    public HttpEntity updateHousehold(@PathVariable(name="id") String householdId, @RequestBody HouseholdDTO household){
        HouseholdDTO result = handler.update(household);
        result.add(linkTo(methodOn(HouseholdController.class).getHouseholdById(result.getId())).withSelfRel());
        return new ResponseEntity<HouseholdDTO>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity getHouseholdById(@PathVariable(name = "id") String id){
        HouseholdDTO household = handler.getHousehold(id);
        return new ResponseEntity<HouseholdDTO>(household, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity deleteHousehold(@PathVariable(name = "id") String id){
        handler.deleteHousehold(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/roommates")
    public HttpEntity getAllRoommates(@PathVariable(name = "id") String id){
        List<RoommateDTO> roommates = handler.getAllRoommates(id);
        roommates.forEach(roommateDTO -> roommateDTO.add(linkTo(methodOn(HouseholdController.class).getHouseholdById(roommateDTO.getId())).withSelfRel()));

        return new ResponseEntity<List<RoommateDTO>>(roommates,HttpStatus.OK);
    }

    @PutMapping("/{householdId}/addRoommate")
    public HttpEntity addRoommate(@PathVariable("householdId") String householdId, @RequestBody String roommateIdJson ){
        JSONObject obj = (JSONObject) JSONValue.parse(roommateIdJson);
        String roommateId = obj.get("roommate_id").toString();
        handler.addRoommate(householdId,roommateId);
        List<RoommateDTO> roommates =handler.getAllRoommates(householdId);
        roommates.forEach(roommateDTO -> roommateDTO.add(linkTo(methodOn(HouseholdController.class).getHouseholdById(householdId)).withSelfRel()));

        return new ResponseEntity<List<RoommateDTO>>(roommates,HttpStatus.OK);
    }

    @DeleteMapping("/{householdId}/removeRoommate/{roommateId}")
    public HttpEntity removeRoommate(@PathVariable("householdId") String householdId, @PathVariable("roommateId") String roommateId){
        handler.removeRoommate(householdId, roommateId);
        HouseholdDTO dto = handler.getHousehold(householdId);
        dto.add(linkTo(methodOn(HouseholdController.class).getHouseholdById(householdId)).withSelfRel());
        return new ResponseEntity<HouseholdDTO>(dto, HttpStatus.OK);
    }

}
