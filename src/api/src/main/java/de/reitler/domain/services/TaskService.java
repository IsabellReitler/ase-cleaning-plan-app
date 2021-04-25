package de.reitler.domain.services;

import de.reitler.domain.entities.Task;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TaskService {

    public Task create(Task task);

    public Task update(Task task);

    public void delete(Task task);

    public void delete(String id);

    public Task getById(String id);

    public void sendTaskToNextRoommate(Task task);

    public List<Task> getAllTasksDoneYesterday(List<Task> tasks);

    public void deleteSimpleTasks(List<Task> tasks);

    public void handleRepetitiveTasks(List<Task> tasks);
}
