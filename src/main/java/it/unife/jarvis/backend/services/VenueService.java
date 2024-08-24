package it.unife.jarvis.backend.services;

import it.unife.jarvis.backend.models.Venue;
import it.unife.jarvis.backend.repositories.VenuesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class VenueService {

	final private VenuesRepository venuesRepository;

	VenueService(VenuesRepository venuesRepository) {
		this.venuesRepository = venuesRepository;
	}

	public Long insert (Venue venue) {
		Venue temp = venuesRepository.save(venue);
		return temp.getId();
	}

	public Venue getOne (Long id) {
		return venuesRepository.findById(id).isPresent() ? venuesRepository.findById(id).get() : null;
	}

	public void delete (Long id) {
		venuesRepository.deleteById(id);
	}

	public List<Venue> listAvailable(String date, String start, String end) {
		Date dateParsed = java.sql.Date.valueOf(date);
		Time startParsed = java.sql.Time.valueOf(start);
		Time endParsed = java.sql.Time.valueOf(end);
		return venuesRepository.findAvailableVenue(dateParsed, startParsed, endParsed);
	}

	public List<Venue> listAvailableByCapacity(Integer max_capacity, String date, String start, String end){
		Date dateParsed = java.sql.Date.valueOf(date);
		Time startParsed = java.sql.Time.valueOf(start);
		Time endParsed = java.sql.Time.valueOf(end);
		return venuesRepository.findAvailableVenueByCapacity(max_capacity, dateParsed, startParsed, endParsed);
	}

	public List<Venue> listAll () {
		return venuesRepository.findAll();
	}
}
