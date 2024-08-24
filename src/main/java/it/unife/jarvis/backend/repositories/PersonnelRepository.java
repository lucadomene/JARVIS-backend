package it.unife.jarvis.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.unife.jarvis.backend.models.Personnel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel, String> {

    @Query("""
           SELECT p
           FROM Personnel p JOIN p.sector s
           WHERE p NOT IN (
                       SELECT p
                       FROM Booking b JOIN b.personnel p
                       WHERE b.date = ?1
                       AND (b.duration.start < ?2 AND b.duration.end > ?3))
           AND ((DAYOFWEEK(?1) NOT IN (1, 7) AND p.weekdayHours.start <= ?2 AND p.weekdayHours.end >= ?3)
           OR (DAYOFWEEK(?1) IN (1, 7) AND p.weekendHours.start <= ?2 AND p.weekendHours.end >= ?3))
           """)
    List<Personnel> getPersonnelAvailability(java.sql.Date date,
                                             java.sql.Time startTime,
                                             java.sql.Time endTime);
}
