package it.unife.jarvis.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.unife.jarvis.backend.models.Personnel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel, String> {

    @Query("""
           SELECT p
           FROM Personnel p JOIN p.sector s
           WHERE s IN ?1
           AND p.name NOT IN (
                       SELECT p.name
                       FROM Booking b JOIN b.personnel p
                       WHERE b.date = ?2
                       AND ((b.duration.start < ?3 AND b.duration.end > ?4))
               AND ((DAYOFWEEK(?2) NOT IN (1, 7) AND p.weekdayHours.start <= ?3 AND p.weekdayHours.end >= ?4)
               OR (DAYOFWEEK(?2) IN (1, 7) AND p.weekdayHours.start <= ?3 AND p.weekdayHours.end >= ?4))
           )""")
    List<Personnel> getPersonnelAvailability(String[] sectors,
                                             java.sql.Date date,
                                             java.sql.Time startTime,
                                             java.sql.Time endTime);
}
