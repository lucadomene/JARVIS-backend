package it.unife.jarvis.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Value
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventInfo {
	String event_id;
	String date;
	String start;
	String end;
	String category;
	int max_partecipants;
}
