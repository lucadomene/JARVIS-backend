package it.unife.jarvis.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Value;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Value
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventInfo {
	int event_id;
	String event_type;
	String date;
	String schedule_start;
	String schedule_end;
	int max_participants;
}
