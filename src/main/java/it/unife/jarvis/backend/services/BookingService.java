package it.unife.jarvis.backend.services;

import it.unife.jarvis.backend.models.Booking;
import it.unife.jarvis.backend.repositories.BookingsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
	@Autowired
	private BookingsRepository bookingsRepository;

	public Long insert (Booking booking) {
		Booking temp = bookingsRepository.save(booking);
		return temp.getId();
	}

	public Booking getOne (Long id) {
		return bookingsRepository.findById(id).get();
	}

	public void delete (Long id) {
		bookingsRepository.deleteById(id);
	}

	public List<Booking> listAll () {
		return bookingsRepository.findAll();
	}
}
