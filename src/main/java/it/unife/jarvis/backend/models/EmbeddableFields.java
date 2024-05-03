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

		public TimeInterval(String completeTimeInterval) {
			String[] splitTimeInterval = completeTimeInterval.split("-");
			this.open = Time.valueOf(splitTimeInterval[0]);
			this.close = Time.valueOf(splitTimeInterval[1]);
		}
	}
}
