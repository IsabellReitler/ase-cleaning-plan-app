package de.reitler.plugin.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDatabaseRepo extends JpaRepository<TaskEntity, String> {
}
