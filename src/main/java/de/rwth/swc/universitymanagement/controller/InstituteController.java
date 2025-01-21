package de.rwth.swc.universitymanagement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.rwth.swc.universitymanagement.model.Institute;
import de.rwth.swc.universitymanagement.repository.InstituteRepository;

/**
 * @author Mohamed Hafidi
 */

@RestController
@RequestMapping("/institutes")
public class InstituteController {
	@Autowired
	InstituteRepository instituteRepository;

	@PostMapping
	public ResponseEntity<Institute> createInstitute(@RequestBody Institute institute) {
		Institute savedInstitute = instituteRepository.save(institute);
		System.out.println("Institite saved with mail " + institute.getMail());
		String location = ServletUriComponentsBuilder.fromCurrentRequest().path("/institutes/{id}")
				.buildAndExpand(savedInstitute.getId()).toUri().toString();
		return ResponseEntity.status(HttpStatus.CREATED).header("location", location).body(savedInstitute);
	}

	@GetMapping("{id}")
	public Institute getInstitute(@PathVariable String id) {
		Optional<Institute> optionalInstitute = instituteRepository.findById(id);
		if (optionalInstitute.isPresent()) {
			return optionalInstitute.get();
		}
		return null;
	}

	@PutMapping("{id}")
	public ResponseEntity<Institute> updateInstitute(@PathVariable String id, @RequestBody Institute institute) {
		Optional<Institute> optionalpdateInstitute = instituteRepository.findById(id);
		if (optionalpdateInstitute.isEmpty() == true) {
			// redirected to creation
			return this.createInstitute(institute);
		}

		else {
			Institute updateInstitute = optionalpdateInstitute.get();
			updateInstitute.setCourses(institute.getCourses());
			updateInstitute.setId(institute.getId());
			updateInstitute.setMail(institute.getMail());
			Institute updatedIntitute = instituteRepository.save(updateInstitute);
			return ResponseEntity.status(HttpStatus.OK).body(updatedIntitute);
		}

	}
}
