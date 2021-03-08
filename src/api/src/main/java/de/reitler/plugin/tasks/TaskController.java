package de.reitler.plugin.tasks;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/task")
public class TaskController {
    
    @PostMapping
    public HttpEntity createTask(@RequestBody String task){
        //TODO
        return null;
    }
    
    @GetMapping("/{id}")
    public HttpEntity getTaskById(@PathVariable(name = "id") String id){
        return null;
    }
    
    @PutMapping(value="/{id}")
    public HttpEntity updateTask(@PathVariable("id") int taskId , @RequestBody String task ) {
        //TODO
        return null;
    }

    @DeleteMapping("/{id}")
    public HttpEntity deleteTask(@PathVariable("id") String id){
        //TODO
        return null;
    }
}
