package de.reitler.plugin.roommate;

import org.aspectj.weaver.ast.Var;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roommate")
public class RoommateController {

    @GetMapping("/{id}")
    public HttpEntity getAllTasks(@PathVariable(name = "id") String roommateId) {
        //TODO
        return null;
    }

    @GetMapping("/{id}/daily")
    public HttpEntity getAllDailyTasks(@PathVariable(name = "id") String roommateId){
        //TODO
        return null;
    }

    @GetMapping("/{id}/weekly")
    public HttpEntity getAllWeeklyTasks(@PathVariable(name = "id") String roommateId){
        //TODO
        return null;
    }

    @PostMapping
    public HttpEntity createRoommate(@RequestBody String body){
        //TODO
        return null;
    }

    @DeleteMapping("/{id}")
    public HttpEntity removeRoommate(@PathVariable(name = "id") String id){
        //TODO
        return null;
    }

    @PutMapping("/{id}")
    public HttpEntity updateRoommate(@PathVariable(name = "id") String id, @RequestBody String body){
        //TODO
        return null;
    }
}