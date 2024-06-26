package it.unife.jarvis.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.unife.jarvis.backend.models.Booking;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingsRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b\n" +
            "FROM Booking b JOIN Venue v ON b.venue = v\n" +
            "WHERE v.name = ?1 OR v.id = ?2")
    List<Booking> getBookingOfVenue(String name, Long id);

   @Query("SELECT b\n" +
            "FROM Booking b JOIN b.personnel p\n" +
            "WHERE p.name = ?1")
    List<Booking> getBookingsOfPersonnel(String name);


}
