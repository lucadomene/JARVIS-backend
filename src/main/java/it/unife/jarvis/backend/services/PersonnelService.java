package it.unife.jarvis.backend.services;

import it.unife.jarvis.backend.models.Personnel;
import it.unife.jarvis.backend.repositories.PersonnelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelService {

	private final PersonnelRepository personnelRepository;

	PersonnelService(PersonnelRepository personnelRepository) {
		this.personnelRepository = personnelRepository;
	}

	public String insert (Personnel personnel) {
		Personnel temp = personnelRepository.save(personnel);
		return temp.getName();
	}

	public Personnel getOne (String name) {
		return personnelRepository.findById(name).get();
	}

	public void delete (String name) {
		personnelRepository.deleteById(name);
	}

	public List<Personnel> listAll () {
		return personnelRepository.findAll();
	}
}
