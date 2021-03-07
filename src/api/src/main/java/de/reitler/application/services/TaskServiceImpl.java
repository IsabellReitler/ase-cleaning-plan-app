package de.reitler.application.services;

import de.reitler.domain.entities.Task;
import de.reitler.domain.repositories.TaskRepository;
import de.reitler.domain.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    TaskRepository repo;

    //@Override
    public void create(Task task) {
        repo.save(task);
    }

   // @Override
    public void update(Task task) {
        repo.save(task);
    }

    //@Override
    public void delete(Task task) {
        repo.delete(task);
    }

    //Override
    public void delete(String id) {
        repo.deleteById(UUID.fromString(id));
    }

    //@Override
    public Task getById(String id) {
        return repo.getOne(UUID.fromString(id));
    }
}
