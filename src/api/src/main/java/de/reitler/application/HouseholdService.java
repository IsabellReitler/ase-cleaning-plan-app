package de.reitler.application;

import de.reitler.domain.entities.Household;
import de.reitler.domain.repositories.HouseholdRepository;

public class HouseholdService implements HouseholdRepository {
    @Override
    public void create(Household household) {

    }

    @Override
    public Household getInitialHousehold() {
        return null;
    }
}
