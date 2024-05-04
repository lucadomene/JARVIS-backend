package it.unife.jarvis.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="venue")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venue {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;

	String name;

	String address;

	int max_capacity;

	double rent_cost;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="open", column = @Column(name="weekdayOpen")),
		@AttributeOverride(name="close", column = @Column(name="weekdayClose"))
	})
	EmbeddableFields.TimeInterval weekdayHours;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="open", column = @Column(name="weekendOpen")),
		@AttributeOverride(name="close", column = @Column(name="weekendClose"))
	})
	EmbeddableFields.TimeInterval weekendHours;

	@ElementCollection
	@CollectionTable(
			name="closingDays",
			joinColumns = @JoinColumn(name = "venueId")
	)
	List<String> closingDays;

	@OneToMany
	@JoinColumn(name="venueId")
	List<Booking> booking;

}
