package it.unife.jarvis.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.unife.jarvis.backend.models.Personnel;

public interface PersonnelRepository extends JpaRepository<Personnel, String> {

}
