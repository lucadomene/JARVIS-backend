package it.unife.jarvis.backend.services;

import it.unife.jarvis.backend.models.Personnel;
import it.unife.jarvis.backend.repositories.PersonnelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelService {
	@Autowired
	private PersonnelRepository personnelRepository;

	public Long insert (Personnel personnel) {
		Personnel temp = PersonnelRepository.save(personnel);
		return temp.getId();
	}

	public Personnel getOne (Long id) {
		return personnelRepository.findById(id).get();
	}

	public void delete (Long id) {
		personnelRepository.deleteById(id);
	}

	public List<Personnel> listAll () {
		return personnelRepository.findAll();
	}
}
