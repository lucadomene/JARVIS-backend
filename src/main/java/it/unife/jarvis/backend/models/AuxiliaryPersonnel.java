package it.unife.jarvis.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="personnel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuxiliaryPersonnel {
	
	@Id
	String name;

	double hourly_cost;

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
			name="sector",
			joinColumns = @JoinColumn(name = "AuxiliaryPersonnelId")
	)
	List<String> sector;

	@ManyToMany
	@JoinTable(name="work_for",
			joinColumns = @JoinColumn(name = "AuxiliaryPersonnelId"),
			inverseJoinColumns = @JoinColumn(name="bookingId")
	)
	Set<Booking> bookings;
}
