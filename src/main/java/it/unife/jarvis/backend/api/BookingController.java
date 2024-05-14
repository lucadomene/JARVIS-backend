package it.unife.jarvis.backend.api;

import it.unife.jarvis.backend.models.Booking;
import it.unife.jarvis.backend.services.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/add")
    public ResponseEntity<String> addNewBooking (@RequestBody Booking booking) {
        Long id = BookingService.insert(booking);
        return ResponseEntity.ok("Entity ID=" + id + " saved successfully\n");
    }

    @GetMapping("/ls")
    public ResponseEntity<List<Booking>> listAllBooking () {
        // HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok(bookingService.listAll());
    }

    @GetMapping("/del")
    public ResponseEntity<String> deleteBooking (@RequestParam Long id) {
        bookingService.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
