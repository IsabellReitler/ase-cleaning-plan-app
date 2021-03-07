package de.reitler.application.services;

import de.reitler.domain.entities.Household;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.repositories.HouseholdRepository;
import de.reitler.domain.services.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    public HouseholdServiceImpl(){
    }


    public void create(Household household){
        householdRepository.save(household);
    }

    public void update(Household household){
        householdRepository.save(household);
    }

    public void delete(Household household){
        householdRepository.delete(household);
    }

    public void delete(String id){
        householdRepository.deleteById((UUID.fromString(id)));
    }

    public Household getById(String id){
        return householdRepository.getOne(UUID.fromString(id));
    }

    @Override
    public List<Roommate> getAllRoommates(String id) {
        return householdRepository.getOne(UUID.fromString(id)).getRoommates();
    }
}
