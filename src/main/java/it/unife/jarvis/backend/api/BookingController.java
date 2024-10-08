package it.unife.jarvis.backend.api;

import it.unife.jarvis.backend.models.Booking;
import it.unife.jarvis.backend.models.BookingDTO;
import it.unife.jarvis.backend.services.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    final private BookingService bookingService;

    BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewBooking (@RequestBody BookingDTO booking) {
        try {
			Long id = bookingService.insert(booking);
			return ResponseEntity.ok(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
		}
    }

    @GetMapping("/ls")
    public ResponseEntity<?> listAllBooking (
            @RequestParam(name = "cf") Optional<String> ssn,
            @RequestParam(name = "personnel") Optional<String> personnel,
            @RequestParam(name = "venue") Optional<String> venue,
            @RequestParam(name = "venueid") Optional<Long> venueid
    ) {
        try {
            if (ssn.isPresent()) {
                return ResponseEntity.ok(bookingService.getBookingsByCF(ssn.get()));
            } else if (personnel.isPresent()) {
                return ResponseEntity.ok(bookingService.getBookingsOfPersonnel(personnel.get()));
            } else if (venue.isPresent() || venueid.isPresent()) {
                return ResponseEntity.ok(bookingService.getBookingOfVenue(venue.get(), venueid.get().toString()));
            } else {
                return ResponseEntity.ok(bookingService.listAll());
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @GetMapping("/del")
    public ResponseEntity<?> deleteBooking (@RequestParam Long id) {
        try {
            bookingService.delete(id);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception exc) {
            exc.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }
}
