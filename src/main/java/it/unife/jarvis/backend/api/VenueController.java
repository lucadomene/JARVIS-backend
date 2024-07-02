package it.unife.jarvis.backend.api;

import it.unife.jarvis.backend.models.Venue;
import it.unife.jarvis.backend.services.VenueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import java.util.List;

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

	@GetMapping("/available")
	public ResponseEntity<List<Venue>> listAvailable (
			@RequestParam String date,
			@RequestParam String start,
			@RequestParam String end
	) {
		List<Venue> venues = venueService.listAvailable(date, start, end);
		return ResponseEntity.ok(venues);
	}

	@GetMapping("/availablebycapacity")
	public ResponseEntity<List<Venue>> listAvailableByCapacity(
			@RequestParam String max_capacity,
			@RequestParam String date,
			@RequestParam String start,
			@RequestParam String end
	){
		List<Venue> venues = venueService.listAvailableByCapacity(max_capacity, date,start,end);
		return ResponseEntity.ok(venues);
	}

	@GetMapping("/ls")
	public ResponseEntity<List<Venue>> listAllVenues () {
		return ResponseEntity.ok(venueService.listAll());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Venue> getVenue (@PathVariable Long id) {
		Venue venue;
		if ((venue = venueService.getOne(id)) != null) {
			return ResponseEntity.ok(venue);
		} else return ResponseEntity.notFound().build();
	}

	@GetMapping("/del")
	public ResponseEntity<String> deleteVenue (@RequestParam Long id) {
		venueService.delete(id);
		return ResponseEntity.ok("Deleted successfully entity with id = " + id);
	}
}
