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

@Service
public class HouseholdServiceImpl implements HouseholdService {


    private HouseholdRepository householdRepository;

    private RoommateRepository roommateRepository;

    @Autowired
    public HouseholdServiceImpl(HouseholdRepository householdRepository, RoommateRepository roommateRepository){
        this.householdRepository = householdRepository;
        this.roommateRepository = roommateRepository;
    }

    @Override
    public Household create(Household household){
        householdRepository.save(household);
        return household;
    }

    @Override
    public Household update(Household household){
        Household old = householdRepository.findById(household.getId()).get();
        old.setName(household.getName());

        householdRepository.save(old);
        return old;
    }

    @Override
    public void delete(Household household){
        householdRepository.findById(household.getId()).get().getRoommates()
                .forEach(roommate -> roommate.setHousehold(null));
        householdRepository.delete(household);
    }

    @Override
    public void delete(String id){
        householdRepository.findById(id).get().getRoommates()
                .forEach(roommate -> roommate.setHousehold(null));
        householdRepository.deleteById(id);
    }

    @Override
    public Household getById(String id){
        return householdRepository.findById(id).get();
    }

    @Override
    public List<Roommate> getAllRoommates(String id) {
        return householdRepository.findById(id).get().getRoommates();
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
        Household household = householdRepository.findById(householdId).get();
        household.removeRoommate(roommateRepository.findById(roommateId).get());
        householdRepository.save(household);
        Roommate roommate = roommateRepository.findById(roommateId).get();
        roommate.setHousehold(null);
        roommateRepository.save(roommate);
        return household;
    }

    @Override
    public List<Household> getAllHouseholds(){
        return householdRepository.findAll();
    }

}

