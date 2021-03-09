package de.reitler.application.services;

import de.reitler.domain.entities.Household;
import de.reitler.domain.entities.Roommate;
import de.reitler.domain.repositories.HouseholdRepository;
import de.reitler.domain.repositories.RoommateRepository;
import de.reitler.domain.services.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    @Autowired
    private RoommateRepository roommateRepository;

    public HouseholdServiceImpl(){
    }

    @Override
    public Household create(Household household){
        householdRepository.save(household);
        return household;
    }

    @Override
    public Household update(Household household){
        Household old = householdRepository.getOne(household.getId());
        old.setName(household.getName());
        old.setRoommates(household.getRoommates());
        householdRepository.save(old);
        return old;
    }

    @Override
    public void delete(Household household){
        householdRepository.delete(household);
    }

    @Override
    public void delete(String id){
        householdRepository.deleteById(id);
    }

    @Override
    public Household getById(String id){
        return householdRepository.getOne(id);
    }

    @Override
    public List<Roommate> getAllRoommates(String id) {
        return householdRepository.getOne(id).getRoommates();
    }

    @Override
    public Household addRoommate(String householdId, String roommateId) {
        Optional<Household> optional = householdRepository.findById(householdId);
        Household household =optional.get();
        Roommate roommate =roommateRepository.findById(roommateId).get();
        household.addRoommate(roommate);
        roommate.setHousehold(household);
        roommateRepository.save(roommate);
        householdRepository.save(household);
        return household;
    }

    @Override
    public Household removeRoommate(String householdId, String roommateId) {
        Household household = householdRepository.getOne(householdId);
        household.removeRoommate(roommateRepository.getOne(roommateId));
        householdRepository.save(household);
        return household;
    }


}

