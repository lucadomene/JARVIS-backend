package it.unife.jarvis.backend.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

public class EmbeddableFields {

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TimeInterval {
		Time open;
		Time close;

	}
}
