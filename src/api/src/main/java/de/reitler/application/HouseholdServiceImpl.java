package de.reitler.application;

import de.reitler.domain.entities.Household;
import de.reitler.domain.repositories.HouseholdRepository;
import de.reitler.domain.services.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HouseholdServiceImpl implements HouseholdService {
    @Autowired
    HouseholdRepository repository;

    public HouseholdServiceImpl(){}

    public void create(Household household){
        repository.save(household);
    }

    public void update(Household household){
        repository.save(household);
    }

    public void delete(Household household){
        repository.delete(household);
    }

    public void delete(String id){
        repository.deleteById((UUID.fromString(id)));
    }

    public Household getById(String id){
        return repository.getOne(UUID.fromString(id));
    }
}
