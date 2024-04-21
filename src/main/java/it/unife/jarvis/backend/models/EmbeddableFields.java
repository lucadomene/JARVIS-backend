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
	public static class Address {
		String street;
		String city;
		String province;
		String cap;
		String state;

		public Address(String completeAddress) {
			String[] splitAddress = completeAddress.split(", ");
			this.street = splitAddress[0];
			this.city = splitAddress[1];
			this.province = splitAddress[2];
			this.cap = splitAddress[3];
			this.state = splitAddress[4];
		}
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TimeInterval {
		Time start;
		Time end;

		public TimeInterval(String completeTimeInterval) {
			String[] splitTimeInterval = completeTimeInterval.split("-");
			this.start = Time.valueOf(splitTimeInterval[0]);
			this.end = Time.valueOf(splitTimeInterval[1]);
		}
	}
}
