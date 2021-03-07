package de.reitler.domain.services;

import de.reitler.domain.entities.Task;
import org.springframework.stereotype.Service;

//@Service
public interface TaskService {

    public void create(Task task);
    public void update(Task task);
    public void delete(Task task);
    public void delete(String id);
    public Task getById(String id);
}
