package de.reitler.application.services;

import de.reitler.domain.entities.Roommate;
import de.reitler.domain.repositories.RoommateRepository;
import de.reitler.domain.services.RoommateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RoommateServiceImpl implements RoommateService {
    @Autowired
    RoommateRepository repository;

    public RoommateServiceImpl(){}

    public void create(Roommate roommate){
        repository.save(roommate);
    }

    public void update(Roommate roommate){
        repository.save(roommate);
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
}
