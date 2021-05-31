package de.reitler.domain.services;

import de.reitler.domain.entities.Household;
import de.reitler.domain.entities.Roommate;

import java.util.List;

public interface HouseholdService {

    public Household create(Household household);

    public Household update(Household household);

    public void delete(Household household);

    public void delete(String id);

    public Household getById(String id);

    public List<Roommate> getAllRoommates(String id);

    public Household addRoommate(String householdId, String roommateId);

    public Household removeRoommate(String householdId, String roommateId);

    public List<Household> getAllHouseholds();
}
