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

import java.sql.Time;
import java.sql.Date;

@Entity
@Table(name="BOOKING")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;

	Date date;

	Time time;
}
