package de.rwth.swc.universitymanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.rwth.swc.universitymanagement.model.Institute;
import de.rwth.swc.universitymanagement.repository.InstituteRepository;

/**
 * @author Mohamed Hafidi
 */

@Service
public class InstituteService {
	
	@Autowired
	InstituteRepository instituteRepository;
	
	public Institute save(Institute institute) {
		return instituteRepository.save(institute);
	}
	
	public Optional<Institute> getById(String id) {
		return instituteRepository.findById(id);
	}
}
