package it.unife.jarvis.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.unife.jarvis.backend.models.Booking;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Booking, Long> {

    @Query("""
            SELECT b
            FROM Booking b JOIN Venue v ON b.venue = v
            WHERE v.name = ?1 OR v.id = ?2""")
    static List<Booking> getBookingOfVenue(String name, Long id) {
        return null;
    }

    @Query("SELECT b\n" +
            "FROM Booking b JOIN b.personnel p\n" +
            "WHERE p.name = ?1")
    static List<Booking> getBookingsOfPersonnel(String name) {
        return null;
    }

    @Query("SELECT b\n" +
            "FROM Booking b\n" +
            "WHERE b.ssn = ?1")
    static List<Booking> getBookingsByCF(String ssn) {
        return null;
    }

}
