package it.unife.jarvis.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import java.sql.Date;

@Entity
@Table(name="venues")
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
		@AttributeOverride(name="start", column = @Column(name="weekday_open")),
		@AttributeOverride(name="end", column = @Column(name="weekday_close"))
	})
	EmbeddableFields.TimeInterval weekdayHours;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="start", column = @Column(name="weekend_open")),
		@AttributeOverride(name="end", column = @Column(name="weekend_close"))
	})
	EmbeddableFields.TimeInterval weekendHours;

	@ElementCollection
	@CollectionTable( name="closing_days", foreignKey = @ForeignKey(
			name = "venue_id",
			foreignKeyDefinition = "foreign key (venue_id) references venues (id) on delete cascade"))
	@Column(name="closing_day")
	List<Date> closingDays;

	// @ManyToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name="venueId")
	// Venue venue;
}
