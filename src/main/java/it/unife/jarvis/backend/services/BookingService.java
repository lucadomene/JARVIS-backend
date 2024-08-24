package it.unife.jarvis.backend.services;

import it.unife.jarvis.backend.models.Booking;
import it.unife.jarvis.backend.models.Venue;
import it.unife.jarvis.backend.models.Personnel;
import it.unife.jarvis.backend.models.BookingDTO;
import it.unife.jarvis.backend.repositories.BookingsRepository;
import it.unife.jarvis.backend.repositories.VenuesRepository;
import it.unife.jarvis.backend.repositories.PersonnelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookingService {

	private final BookingsRepository bookingsRepository;
	private final VenuesRepository venuesRepository;
	private final PersonnelRepository personnelRepository;

	BookingService(BookingsRepository br, VenuesRepository vr, PersonnelRepository pr) {
		bookingsRepository = br;
		venuesRepository = vr;
		personnelRepository = pr;
	}

	public Long insert (BookingDTO booking) throws Exception{
		Venue venue = this.venuesRepository.findById(booking.getVenueId()).orElseThrow(() -> new Exception("invalid venue ID"));

		Set<Personnel> personnel = new HashSet<Personnel>();
		Iterator<String> personnelNameIterator = booking.getPersonnelName().iterator();
		while(personnelNameIterator.hasNext()) {
			Personnel tempPersonnel = this.personnelRepository.findById(personnelNameIterator.next()).orElseThrow(() -> new Exception("invalid personnel Name"));
			personnel.add(tempPersonnel);
		}
		Booking tempBooking = new Booking();
		tempBooking.setDate(booking.getDate());
		tempBooking.setSsn(booking.getSsn());
		tempBooking.setDuration(booking.getDuration());
		tempBooking.setVenue(venue);
		tempBooking.setPersonnel(personnel);

		return (bookingsRepository.save(tempBooking)).getId();
	}

	public Booking getOne (Long id) {
		return bookingsRepository.findById(id).get();
	}

	public void delete (Long id) {
		bookingsRepository.deleteById(id);
	}

	public List<Booking> getBookingOfVenue(String name, String id) {
		Long idParsed = Long.parseLong(id);
		return bookingsRepository.getBookingOfVenue(name, idParsed);
	}

	public List<Booking> getBookingsOfPersonnel(String name){
		return bookingsRepository.getBookingsOfPersonnel(name);
	}

	public List<Booking> getBookingsByCF(String ssn){
		return bookingsRepository.getBookingsByCF(ssn);
	}

	public List<Booking> listAll () {
		return bookingsRepository.findAll();
	}
}
