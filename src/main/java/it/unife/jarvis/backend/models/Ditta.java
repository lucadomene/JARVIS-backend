package it.unife.jarvis.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="DITTA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ditta {
	
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
}
