package de.reitler.domain.repositories;

import de.reitler.domain.entities.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HouseholdRepository extends JpaRepository<Household, String> {
}
