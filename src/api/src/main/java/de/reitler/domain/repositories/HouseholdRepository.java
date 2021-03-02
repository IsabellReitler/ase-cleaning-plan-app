package de.reitler.domain.repositories;

import de.reitler.domain.entities.Household;

public interface HouseholdRepository {

    public void create(Household household);
    public Household getInitialHousehold();

}
