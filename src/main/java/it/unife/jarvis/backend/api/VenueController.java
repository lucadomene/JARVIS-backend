package it.unife.jarvis.backend.api;

import it.unife.jarvis.backend.models.Venue;
import it.unife.jarvis.backend.services.VenueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/venue")
public class VenueController {

	final private VenueService venueService;

	VenueController(VenueService venueService) {
		this.venueService = venueService;
	}

	@PostMapping("/add")
	public ResponseEntity<String> addNewVenue (@RequestBody Venue venue) {
		Long id = venueService.insert(venue);
		return ResponseEntity.ok("Entity ID=" + id + " saved successfully\n");
	}

//	@GetMapping("/available")
//	public ResponseEntity<List<Venue>> listAvailable (
//			@RequestParam String date,
//			@RequestParam String start,
//			@RequestParam String end
//	) {
//		List<Venue> venues = venueService.listAvailable(date, start, end);
//		return ResponseEntity.ok(venues);
//	}

	@GetMapping("/available")
	public ResponseEntity<?> listAvailableByCapacity(
			@RequestParam(name = "capacity") Optional<Integer> max_capacity,
			@RequestParam String date,
			@RequestParam String start,
			@RequestParam String end
	){
		try {
			List<Venue> venues;
			if (max_capacity.isPresent()) {
				venues = venueService.listAvailableByCapacity(max_capacity.get(), date,start,end);
			} else {
				venues = venueService.listAvailable(date, start, end);
			}
			return ResponseEntity.ok(venues);
		} catch (Exception exc) {
			exc.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
		}
	}

	@GetMapping("/ls")
	public ResponseEntity<?> listAllVenues () {
		try {
			return ResponseEntity.ok(venueService.listAll());
		} catch (Exception exc) {
			exc.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getVenue (@PathVariable Long id) {
		try {
			Venue venue;
			if ((venue = venueService.getOne(id)) != null) {
				return ResponseEntity.ok(venue);
			} else return ResponseEntity.notFound().build();
		} catch (Exception exc) {
			exc.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
		}
	}

	@GetMapping("/del")
	public ResponseEntity<String> deleteVenue (@RequestParam Long id) {
		try {
			venueService.delete(id);
			return ResponseEntity.ok("Deleted successfully entity with id = " + id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
		}
	}
}
