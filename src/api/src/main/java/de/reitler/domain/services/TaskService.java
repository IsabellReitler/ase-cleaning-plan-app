package de.reitler.domain.services;

import de.reitler.domain.entities.Task;
import org.springframework.stereotype.Service;


public interface TaskService {

    public Task create(Task task);
    public Task update(Task task);
    public void delete(Task task);
    public void delete(String id);
    public Task getById(String id);
}
