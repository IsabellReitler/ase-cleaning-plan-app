package de.reitler.application.services;

import de.reitler.domain.entities.Roommate;
import de.reitler.domain.entities.Task;
import de.reitler.domain.repositories.RoommateRepository;
import de.reitler.domain.repositories.TaskRepository;
import de.reitler.domain.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    TaskRepository repo;

    @Autowired
    RoommateRepository roommateRepository;

    //@Override
    public Task create(Task task) {
        return repo.save(task);
    }

   // @Override
    public Task update(Task task) {
        return repo.save(task);
    }

    //@Override
    public void delete(Task task) {
        Roommate roommate = repo.findById(task.getId()).get().getRoommate();
        roommateRepository.findById(roommate.getId()).get().removeTask(task);
        repo.delete(task);
    }

    //Override
    public void delete(String id) {
        Roommate roommate = repo.findById(UUID.fromString(id)).get().getRoommate();
        roommateRepository.findById(roommate.getId()).get().removeTask(repo.findById(UUID.fromString(id)).get());
        repo.deleteById(UUID.fromString(id));
    }

    //@Override
    public Task getById(String id) {
        return repo.getOne(UUID.fromString(id));
    }
}
