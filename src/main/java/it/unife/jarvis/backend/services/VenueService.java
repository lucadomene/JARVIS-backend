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
	@Autowired
	private VenuesRepository venuesRepository;

	public Long insert (Venue venue) {
		Venue temp = venuesRepository.save(venue);
		return temp.getId();
	}

	public Venue getOne (Long id) {
		return venuesRepository.findById(id).get();
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

	public List<Venue> listAll () {
		return venuesRepository.findAll();
	}
}
