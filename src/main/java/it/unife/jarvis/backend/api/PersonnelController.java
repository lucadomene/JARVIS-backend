package it.unife.jarvis.backend.api;

import it.unife.jarvis.backend.models.Personnel;
import it.unife.jarvis.backend.models.Venue;
import it.unife.jarvis.backend.services.PersonnelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/personnel")
public class PersonnelController {

    final private PersonnelService personnelService;

    PersonnelController(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewPersonnel (@RequestBody Personnel personnel) {
        String name = personnelService.insert(personnel);
        return ResponseEntity.ok("Entity ID=" + name + " saved successfully\n");
    }

    // Chiamata esempio:
    // http://localhost:8080/api/personnel/available?sectors=ricevimento,matrimonio&date=2024-05-16&start=14:00:00&end=18:00:00
    @GetMapping("/available")
    public ResponseEntity<?> listAvailable (
            @RequestParam String[] sectors,
            @RequestParam String date,
            @RequestParam String start,
            @RequestParam String end
    ) {
        try {
            List<Personnel> personnel = personnelService.listAvailable(sectors, date, start, end);
            return ResponseEntity.ok(personnel);
        } catch (Exception exc) {
            exc.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @GetMapping("/ls")
    public ResponseEntity<?> listAllPersonnel () {
        try {
            return ResponseEntity.ok(personnelService.listAll());
        } catch (Exception exc) {
            exc.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @GetMapping("/del")
    public ResponseEntity<?> deletePersonnel (@RequestParam String name) {
        try {
            personnelService.delete(name);
            return ResponseEntity.ok("Deleted successfully");
        } catch (Exception exc) {
            exc.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }
}
