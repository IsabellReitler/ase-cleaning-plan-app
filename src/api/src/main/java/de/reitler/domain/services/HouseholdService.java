package de.reitler.domain.services;

import de.reitler.domain.entities.Household;
import org.springframework.stereotype.Service;

@Service
public interface HouseholdService {

    public void create(Household household);

    public void update(Household household);

    public void delete(Household household);

    public void delete(String id);

    public Household getById(String id);
}
