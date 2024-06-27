package it.unife.jarvis.backend.api;

import it.unife.jarvis.backend.models.Personnel;
import it.unife.jarvis.backend.services.PersonnelService;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/ls")
    public ResponseEntity<List<Personnel>> listAllPersonnel () {
        // HttpHeaders responseHeaders = new HttpHeaders();
        return ResponseEntity.ok(personnelService.listAll());
    }

    @GetMapping("/del")
    public ResponseEntity<String> deletePersonnel (@RequestParam String name) {
        personnelService.delete(name);
        return ResponseEntity.ok("Deleted successfully");
    }
}
