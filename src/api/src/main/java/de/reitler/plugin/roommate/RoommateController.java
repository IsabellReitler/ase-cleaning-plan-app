package de.reitler.plugin.roommate;

import de.reitler.application.roommate.RoommateDTO;
import de.reitler.application.roommate.RoommateHandler;
import de.reitler.application.tasks.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/roommate")
public class RoommateController {

    @Autowired
    private RoommateHandler handler;

    @GetMapping("/{id}")
    public HttpEntity getRoommate(@PathVariable(name = "id") String id){
        RoommateDTO roommate = null;
        try {
            roommate = handler.getById(id);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if(roommate != null) {
            return new ResponseEntity<RoommateDTO>(roommate, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/tasks")
    public HttpEntity getAllTasks(@PathVariable(name = "id") String roommateId) {
        List<TaskDTO> tasks = handler.getAllTasks(roommateId);
        return new ResponseEntity<List<TaskDTO>>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}/tasks/daily")
    public HttpEntity getAllDailyTasks(@PathVariable(name = "id") String roommateId){
        List<TaskDTO> dailyTasks = handler.getAllDailyTasks(roommateId);
        return new ResponseEntity(dailyTasks, HttpStatus.OK);
    }

    @GetMapping("/{id}/tasks/weekly")
    public HttpEntity getAllWeeklyTasks(@PathVariable(name = "id") String roommateId){
        List<TaskDTO> weeklyTasks = handler.getAllWeeklyTasks(roommateId);
        return new ResponseEntity(weeklyTasks, HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity createRoommate(@RequestBody RoommateDTO body){
        RoommateDTO account = handler.create(body);
        account.add(linkTo(methodOn(RoommateController.class).getRoommate(account.getId())).withSelfRel());
        if(account != null){
            return new ResponseEntity<RoommateDTO>(account, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public HttpEntity deleteRoommate(@PathVariable(name = "id") String id){
        //TODO
        return null;
    }

    @PutMapping("/{id}")
    public HttpEntity updateRoommate(@PathVariable(name = "id") String id, @RequestBody RoommateDTO body){
        RoommateDTO roommate = null;
        try {
            roommate = handler.update(body);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if(roommate!= null){
           roommate.add(linkTo(methodOn(RoommateController.class).getRoommate(roommate.getId())).withSelfRel());
           return new ResponseEntity<RoommateDTO>(roommate, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}