package de.reitler.domain.services;

import de.reitler.domain.entities.Task;

public interface TaskService {

    public void create(Task task);
    public void update(Task task);
    public void delete(Task task);
    public void delete(String id);
    public Task getById(String id);
}
