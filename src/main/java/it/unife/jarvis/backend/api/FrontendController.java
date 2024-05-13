package it.unife.jarvis.backend.api;

import it.unife.jarvis.backend.components.RESTConsumer;
import it.unife.jarvis.backend.models.EventInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/callREST")
public class FrontendController {

    @Autowired
    RESTConsumer consumer;

    @GetMapping("/getEvent")
    public @ResponseBody EventInfo getEventInfo () {
        EventInfo output = consumer.consumeRESTParse("http://localhost:3000/api/mock_supplier", EventInfo.class);
        return output;
    }

    @GetMapping("/getEvents")
    public @ResponseBody List<EventInfo> getEventsInfo () {
        List<EventInfo> events = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            EventInfo e = consumer.consumeRESTParse("http://localhost:3000/api/mock_supplier", EventInfo.class);
            events.add(e);
        }
        return events;
    }
}
