package de.reitler.domain.services;

import de.reitler.domain.entities.Roommate;
import org.springframework.stereotype.Service;

public interface RoommateService {

    public void create(Roommate roommate);

    public void update(Roommate roommate);

    public void delete(Roommate roommate);

    public void delete(String id);

    public Roommate getById(String id);
}
