package de.reitler.application;

import de.reitler.domain.entities.Roommate;
import de.reitler.domain.repositories.RoommateRepository;

import java.util.ArrayList;
import java.util.List;

public class RoommateService implements RoommateRepository {
    private List<Roommate> roommates = new ArrayList<>();
    @Override
    public void create(Roommate roommate) {
        roommates.add(roommate);
    }

    @Override
    public Roommate getByID(String Id) {
        return null;
    }
}
