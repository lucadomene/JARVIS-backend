package it.unife.jarvis.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.unife.jarvis.backend.models.Booking;

public interface BookingsRepository extends JpaRepository<Booking, Long> {

}
