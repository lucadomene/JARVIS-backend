package it.unife.jarvis.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Date;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;

	Date date;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="start", column = @Column(name="start_time")),
		@AttributeOverride(name="end", column = @Column(name="end_time"))
	})
	EmbeddableFields.TimeInterval duration;

	@Column(name = "ssn", nullable = false, length = 16)
	String ssn;

	@ManyToOne
	@JoinColumn(name="venue_id")
	Venue venue;

	@ManyToMany
	@JoinTable(name="work_for",
			joinColumns = @JoinColumn(name = "booking_id"),
			inverseJoinColumns = @JoinColumn(name="personnel_name")
	)
	Set<Personnel> personnel;
}
