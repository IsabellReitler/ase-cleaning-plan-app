package de.reitler.application;

import de.reitler.domain.Household;
import de.reitler.domain.HouseholdRepository;

public class HouseholdRepoIml implements HouseholdRepository {
    @Override
    public void create(Household household) {

    }

    @Override
    public Household getInitialHousehold() {
        return null;
    }
}
