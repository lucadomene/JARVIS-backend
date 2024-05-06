package it.unife.jarvis.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.unife.jarvis.backend.models.Personnel;

public interface PersonnelRepository extends JpaRepository<Personnel, String> {

}
