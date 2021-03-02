package de.reitler.domain.repositories;

import de.reitler.domain.entities.Roommate;

public interface RoommateRepository {

    public void create(Roommate roommate);
    public Roommate getByID(String Id);

}
