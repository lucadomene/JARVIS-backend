package it.unife.jarvis.backend.api;

import it.unife.jarvis.backend.models.Venue;
import it.unife.jarvis.backend.services.DatabaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/database")
public class DatabaseController {

	@Autowired
	private DatabaseService databaseService;

	@PostMapping("/add")
	public @ResponseBody String addNewVenue (@RequestParam String name, @RequestParam String address, @RequestParam String openHours) {
		databaseService.insert(name, address, openHours);
		return "Saved successfully";
	}

	@GetMapping("/ls")
	public @ResponseBody List<Venue> listAllVenues () {
		return databaseService.listAll();
	}

	@GetMapping("/del/{venueID}")
	public String deleteVenue (@PathVariable("venueID") Long id) {
		databaseService.delete(id);
		return "Deleted successfully";
	}
}
