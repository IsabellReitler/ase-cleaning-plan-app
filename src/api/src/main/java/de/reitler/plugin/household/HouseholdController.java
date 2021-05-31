package de.reitler.plugin.household;



import de.reitler.application.dtos.*;
import de.reitler.application.dtos.TaskDTO;
import de.reitler.application.handlers.HouseholdHandler;
import de.reitler.application.handlers.RoommateHandler;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/household")
public class HouseholdController {

    @Autowired
    HouseholdHandler handler;

    @Autowired
    RoommateHandler roommateHandler;

    @PostMapping
    public HttpEntity createHousehold(@RequestBody HouseholdDTO household){
            HouseholdDTO dto = handler.createHousehold(household);
            if(dto != null) {
                return new ResponseEntity<HouseholdDTO>(dto, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
    }

    @PutMapping("/{id}")
    public HttpEntity updateHousehold(@PathVariable(name="id") String householdId, @RequestBody HouseholdDTO household){
        HouseholdDTO result = handler.update(household);
        return new ResponseEntity<HouseholdDTO>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity getHouseholdById(@PathVariable(name = "id") String id){
        HouseholdDTO household = handler.getHousehold(id);
        return new ResponseEntity<HouseholdDTO>(household, HttpStatus.OK);
    }

    @GetMapping("/{id}/allTasks")
    public HttpEntity getAllTasks(@PathVariable(name = "id") String id){
        List<TaskDTO> tasks = new ArrayList<>();
        List<RoommateDTO> roommates = handler.getAllRoommates(id);
        for(RoommateDTO r : roommates){
            tasks.addAll(roommateHandler.getAllTasks(r.getId()));
        }
        return new ResponseEntity<List<TaskDTO>>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity deleteHousehold(@PathVariable(name = "id") String id){
        handler.deleteHousehold(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/roommates")
    public HttpEntity getAllRoommates(@PathVariable(name = "id") String id){
        List<RoommateDTO> roommates = handler.getAllRoommates(id);
        return new ResponseEntity<List<RoommateDTO>>(roommates,HttpStatus.OK);
    }

    @PutMapping("/{householdId}/addRoommate")
    public HttpEntity addRoommate(@PathVariable("householdId") String householdId, @RequestBody String roommateIdJson ){
        JSONObject obj = (JSONObject) JSONValue.parse(roommateIdJson);
        String roommateId = obj.get("roommate_id").toString();
        handler.addRoommate(householdId,roommateId);
        List<RoommateDTO> roommates =handler.getAllRoommates(householdId);
        return new ResponseEntity<List<RoommateDTO>>(roommates,HttpStatus.OK);
    }

    @DeleteMapping("/{householdId}/removeRoommate/{roommateId}")
    public HttpEntity removeRoommate(@PathVariable("householdId") String householdId, @PathVariable("roommateId") String roommateId){
        handler.removeRoommate(householdId, roommateId);
        HouseholdDTO dto = handler.getHousehold(householdId);
        return new ResponseEntity<HouseholdDTO>(dto, HttpStatus.OK);
    }

    @GetMapping("/withRoommate/{roommateId}")
    public HttpEntity getHouseholdFromRoommate(@PathVariable("roommateId") String roommateId){
        HouseholdDTO dto = handler.findHouseholdFromRoommate(roommateId);
        return new ResponseEntity(dto, HttpStatus.OK);
    }
}
