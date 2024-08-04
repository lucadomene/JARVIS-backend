package it.unife.jarvis.backend.api;

import it.unife.jarvis.backend.components.RESTConsumer;
import it.unife.jarvis.backend.models.EventInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/callREST")
public class FrontendController {

    RESTConsumer consumer;

    FrontendController(RESTConsumer consumer) {
        this.consumer = consumer;
    }

    @GetMapping("/getEvent")
    public ResponseEntity<?> getEventInfo () {
        try {
            EventInfo output = consumer.consumeRESTParse("http://localhost:3000/api/mock_supplier", EventInfo.class);
            return ResponseEntity.ok(output);
        } catch (Exception exc) {
            exc.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @GetMapping("/getEvents")
    public ResponseEntity<?> getEventsInfo () {
        try {
            List<EventInfo> events = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                EventInfo e = consumer.consumeRESTParse("http://localhost:3000/api/mock_supplier", EventInfo.class);
                events.add(e);
            }
            return ResponseEntity.ok(events);
        } catch (Exception exc) {
            exc.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
        }
    }

    @GetMapping("/getUser")
    public @ResponseBody String getUser () {
        String output = consumer.consumeRESTParse("http://localhost:3000/api/fetchUser", String.class);
        return output;
    }
}
