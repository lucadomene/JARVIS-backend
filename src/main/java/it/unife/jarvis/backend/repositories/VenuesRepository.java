package it.unife.jarvis.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.unife.jarvis.backend.models.Venue;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VenuesRepository extends JpaRepository<Venue, Long> {
    @Query("""
            SELECT v
            FROM Venue v
            WHERE NOT EXISTS (SELECT b
                              FROM Booking b
                              WHERE b.venue=v
                                AND b.date = ?1
                                AND (b.duration.start >= ?2 OR b.duration.end <= ?3 ))
                AND ((DAYOFWEEK(?1) NOT IN (1, 7) AND v.weekdayHours.start <= ?2 AND v.weekdayHours.end >= ?3)
                OR (DAYOFWEEK(?1) IN (1, 7) AND v.weekendHours.start <= ?2 AND v.weekendHours.end >= ?3))""")
    List<Venue> findAvailableVenue(java.sql.Date date, java.sql.Time start, java.sql.Time end);

    @Query("""
            SELECT v
            FROM Venue v
            WHERE v.max_capacity>=?1 AND NOT EXISTS (SELECT b
                                                    FROM Booking b
                                                    WHERE b.venue=v AND b.date = ?2
                                                    AND (b.duration.start <= ?3 AND b.duration.end >= ?4))
            AND ((DAYOFWEEK(?2) NOT IN (1,7) AND v.weekdayHours.start <= ?3 AND v.weekdayHours.end >= ?4)
            OR (DAYOFWEEK(?2) IN (1,7) AND v.weekendHours.start <= ?3 AND v.weekendHours.end >= ?4))""")
    List<Venue> findAvailableVenueByCapacity(Integer max_capacity, java.sql.Date date, java.sql.Time start, java.sql.Time end);
}
