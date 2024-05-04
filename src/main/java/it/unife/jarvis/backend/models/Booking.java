package it.unife.jarvis.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Date;

@Entity
@Table(name="booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;

	Date date;

	Time time;

	@ManyToOne
	@JoinColumn(name="venue")
	Venue venue;
}
