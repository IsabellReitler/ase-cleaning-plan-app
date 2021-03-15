package de.reitler.application.services;

import de.reitler.domain.entities.Roommate;
import de.reitler.domain.entities.Task;
import de.reitler.domain.repositories.RoommateRepository;
import de.reitler.domain.services.RoommateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoommateServiceImpl implements RoommateService {
    @Autowired
    RoommateRepository repository;

    public RoommateServiceImpl(){}

    public Roommate create(Roommate roommate){
        return repository.save(roommate);
    }

    public Roommate update(Roommate roommate){
        Roommate old = repository.findById(roommate.getId()).get();
        old.setDisplayname(roommate.getDisplayname());
        old.setEmail(roommate.getEmail());
        old.setPicture(roommate.getPicture());
        old.setHolidayMode(roommate.getHolidayMode());
        return repository.save(old);
    }

    public void delete(Roommate roommate){
        repository.delete(roommate);
    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public Roommate getById(String id){
        return repository.getOne(id);
    }

    @Override
    public List<Task> getAllTasks(String id) {
        return repository.getOne(id).getTasks();
    }
}
