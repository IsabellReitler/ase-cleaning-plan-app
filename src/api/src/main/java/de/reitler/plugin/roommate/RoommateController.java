package de.reitler.plugin.roommate;

import de.reitler.application.roommate.RoommateDTO;
import de.reitler.application.roommate.RoommateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/roommate")
public class RoommateController {

    @Autowired
    private RoommateHandler handler;

    @GetMapping("/{id}")
    public HttpEntity getRoommate(@PathVariable(name = "id") String id){
        RoommateDTO roommate = handler.getById(id);
        if(roommate != null) {
            return new ResponseEntity<RoommateDTO>(roommate, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/tasks")
    public HttpEntity getAllTasks(@PathVariable(name = "id") String roommateId) {
        //TODO
        return null;
    }

    @GetMapping("/{id}/tasks/daily")
    public HttpEntity getAllDailyTasks(@PathVariable(name = "id") String roommateId){
        //TODO
        return null;
    }

    @GetMapping("/{id}/tasks/weekly")
    public HttpEntity getAllWeeklyTasks(@PathVariable(name = "id") String roommateId){
        //TODO
        return null;
    }

    @PostMapping
    public HttpEntity createRoommate(@RequestBody RoommateDTO body){
        RoommateDTO account = handler.create(body);
        if(account != null){
            return new ResponseEntity<RoommateDTO>(account, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public HttpEntity removeRoommate(@PathVariable(name = "id") String id){
        //TODO
        return null;
    }

    @PutMapping("/{id}")
    public HttpEntity updateRoommate(@PathVariable(name = "id") String id, @RequestBody RoommateDTO body){
        RoommateDTO roommate = handler.update(body);
        if(roommate!= null){
           roommate.add(linkTo(methodOn(RoommateController.class).getRoommate(roommate.getId())).withSelfRel());
           return new ResponseEntity<RoommateDTO>(roommate, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}