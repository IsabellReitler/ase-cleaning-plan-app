package de.reitler.application;

import de.reitler.domain.Roommate;
import de.reitler.domain.RoommateRepository;

import java.util.List;

public class RoommateRepoImpl implements RoommateRepository {
    private List<Roommate> roommates;
    @Override
    public void create(Roommate roommate) {
        roommates.add(roommate);
    }

    @Override
    public Roommate getByID(String Id) {
        return null;
    }
}
