package de.reitler.plugin.tasks;

import de.reitler.application.dtos.*;
import de.reitler.application.handlers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskHandler handler;
    
    @PostMapping
    public HttpEntity createTask(@RequestBody TaskDTO task){
        TaskDTO dto = handler.create(task);
        return new ResponseEntity(dto, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public HttpEntity getTaskById(@PathVariable(name = "id") String id){
        TaskDTO dto = handler.getById(id);
        return new ResponseEntity(dto, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public HttpEntity updateTask(@PathVariable("id") String taskId , @RequestBody TaskDTO task ) {
        TaskDTO dto = handler.update(task);
        if(dto != null){
            return new ResponseEntity<TaskDTO>(dto, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public HttpEntity deleteTask(@PathVariable("id") String id){
        handler.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
