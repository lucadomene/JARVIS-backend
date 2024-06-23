package it.unife.jarvis.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.unife.jarvis.backend.models.Venue;
import it.unife.jarvis.backend.models.Booking;
import it.unife.jarvis.backend.models.BookingDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// import java.sql.Time;
// import java.sql.Date;

public interface VenuesRepository extends JpaRepository<Venue, Long> {
    @Query("SELECT v\n" +
            "FROM Venue v\n" +
            "WHERE NOT EXISTS (SELECT b\n" +
            "                  FROM Booking b\n" +
            "                  WHERE b.venue=v\n" +
            "                    AND b.date = ?1\n" +
            "                    AND ((b.duration.start >= ?2 OR b.duration.end <= ?3 )))\n" +
            "    AND ((DAYOFWEEK(?1) NOT IN (1, 7) AND v.weekdayHours.start <= ?2 AND v.weekdayHours.end >= ?3)\n" +
            "        OR (DAYOFWEEK(?1) IN (1, 7) AND v.weekendHours.start <= ?2 AND v.weekendHours.end >= ?3))")
    List<Venue> findAvailableVenue(java.sql.Date date, java.sql.Time start, java.sql.Time end);

    @Query("SELECT b\n" +
            "FROM Booking b JOIN Venue v ON b.venue = v\n" +
            "WHERE v.name = ?1 OR v.id = ?2")
    List<Booking> getBokkingOfVenue(String name, Long id);
}