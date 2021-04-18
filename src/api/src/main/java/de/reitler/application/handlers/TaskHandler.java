package de.reitler.application.handlers;

import de.reitler.application.tasks.TaskDTO;
import de.reitler.domain.entities.Task;
import de.reitler.domain.services.RoommateService;
import de.reitler.domain.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskHandler {

    @Autowired
    TaskService service;

    @Autowired
    RoommateService roommateService;

    public TaskDTO create(TaskDTO body){
        Task task;
        if(body.getTimeInterval() == 0){
            task = new Task(body.getTitle(), body.getDescription(), body.getDeadline(), roommateService.getById(body.getRoommateID()));
        } else {
            task = new Task(body.getTitle(), body.getDescription(),body.getTimeInterval(), body.isSwitchRoommate(),roommateService.getById(body.getRoommateID()));
        }
        task = service.create(task);
        return new TaskDTO(task.getId().toString(), task.getTitle(), task.getDescription(), task.getStartsAt(), task.getDeadline(), task.getDoneAt(), task.getTimeIntervall(), task.isSwitchRoommate(), task.getRoommate().getId());
    }

    public TaskDTO update(TaskDTO dto){
        Task task = service.update(new Task(UUID.fromString(dto.getId()), dto.getTitle(), dto.getDescription(),dto.getStartsAt(),dto.getDeadline(),dto.getDoneAt(),dto.getTimeInterval(),dto.isSwitchRoommate(),roommateService.getById(dto.getRoommateID())));
        return new TaskDTO(task.getId().toString(), task.getTitle(), task.getDescription(), task.getStartsAt(), task.getDeadline(), task.getDoneAt(), task.getTimeIntervall(), task.isSwitchRoommate(), task.getRoommate().getId());
    }

    public void delete(String id){
        service.delete(id);
    }

    public TaskDTO getById(String id){
        Task task = service.getById(id);
        return new TaskDTO(task.getId().toString(), task.getTitle(), task.getDescription(), task.getStartsAt(), task.getDeadline(), task.getDoneAt(), task.getTimeIntervall(), task.isSwitchRoommate(), task.getRoommate().getId());
    }


}
