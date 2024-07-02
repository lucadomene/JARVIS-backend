package it.unife.jarvis.backend.models;

import lombok.Data;
import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.*;

@Data
public class BookingDTO {

	Date date;

	EmbeddableFields.TimeInterval duration;

	String ssn;

	@JsonProperty("venue_id")
	Long venueId;

	@JsonProperty("personnel_name")
	Set<String> personnelName;
}
