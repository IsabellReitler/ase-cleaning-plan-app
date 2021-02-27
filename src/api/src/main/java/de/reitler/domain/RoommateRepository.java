package de.reitler.domain;

public interface RoommateRepository {

    public void create(Roommate roommate);
    public Roommate getByID(String Id);

}
