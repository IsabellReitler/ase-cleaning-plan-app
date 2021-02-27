package de.reitler.domain;

public interface HouseholdRepository {

    public void create(Household household);
    public Household getInitialHousehold();

}
