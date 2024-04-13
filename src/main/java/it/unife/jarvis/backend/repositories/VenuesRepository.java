package it.unife.jarvis.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.unife.jarvis.backend.models.Venue;

public interface VenuesRepository extends JpaRepository<Venue, Long> {

}
