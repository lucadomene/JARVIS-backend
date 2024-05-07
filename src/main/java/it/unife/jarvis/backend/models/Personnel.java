package it.unife.jarvis.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="personnel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personnel {
	
	@Id
	String name;

	double hourly_cost;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="start", column = @Column(name="weekday_open")),
		@AttributeOverride(name="end", column = @Column(name="weekday_close"))
	})
	EmbeddableFields.TimeInterval WEEKDAY_Hours;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="start", column = @Column(name="weekend_open")),
		@AttributeOverride(name="end", column = @Column(name="weekend_close"))
	})
	EmbeddableFields.TimeInterval weekendHours;

	@ElementCollection
	@CollectionTable( name="sectors", foreignKey = @ForeignKey(
			name = "personnel_name",
			foreignKeyDefinition = "foreign key (personnel_name) references personnel (name) on delete cascade"))
	@Column(name="sector")
	List<String> sector;
}
