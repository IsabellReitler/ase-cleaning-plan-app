package de.reitler.domain.repositories;

import de.reitler.domain.entities.Roommate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoommateRepository extends JpaRepository<Roommate, String> {

}
