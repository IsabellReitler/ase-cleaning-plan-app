package de.reitler.domain.services;

import de.reitler.domain.entities.Roommate;
import de.reitler.domain.entities.Task;

import java.util.List;

public interface RoommateService {

    public Roommate create(Roommate roommate);

    public Roommate update(Roommate roommate);

    public void delete(Roommate roommate);

    public void delete(String id);

    public Roommate getById(String id);

    public List<Task> getAllTasks(String id);
}
