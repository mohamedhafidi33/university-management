package de.rwth.swc.universitymanagement.controller;

import java.util.Arrays;

import java.util.List;
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

import de.rwth.swc.universitymanagement.dto.CourseRequest;
import de.rwth.swc.universitymanagement.dto.CourseResponse;
import de.rwth.swc.universitymanagement.dto.InstituteResponse;
import de.rwth.swc.universitymanagement.model.Course;
import de.rwth.swc.universitymanagement.model.Institute;
import de.rwth.swc.universitymanagement.model.Student;
import de.rwth.swc.universitymanagement.service.CourseService;
import de.rwth.swc.universitymanagement.service.InstituteService;

/**
 * @author Mohamed Hafidi
 */

@RestController
@RequestMapping("/courses")
public class CourseController {
	@Autowired
	CourseService courseService;
	@Autowired
	InstituteService instituteService;

	@PostMapping
	public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) {
		Optional<Institute> optionalInstitite = instituteService.getById(courseRequest.getInstituteId());
		Course course = new Course();
		CourseResponse courseResponse = new CourseResponse();
		InstituteResponse instituteResponse = new InstituteResponse();
		if (optionalInstitite.isEmpty() != true) {
			course.setId(courseRequest.getId());
			course.setCredits(courseRequest.getCredits());
			course.setInstitute(optionalInstitite.get());
			course.setName(courseRequest.getName());
			Course savedCourse = courseService.save(course);
			System.out.println("Course saved with id " + savedCourse.getId());
			instituteResponse.setCourses(Arrays.asList(savedCourse.getId()));
			instituteResponse.setId(savedCourse.getInstitute().getId());
			instituteResponse.setMail(savedCourse.getInstitute().getMail());
			courseResponse.setCredits(savedCourse.getCredits());
			courseResponse.setInstitute(instituteResponse);
			courseResponse.setId(savedCourse.getId());
			courseResponse.setName(savedCourse.getName());
			List<Student> students = savedCourse.getStudents();
			List<Student> responseStudents = students != null ? savedCourse.getStudents() : Arrays.asList();
			courseResponse.setStudents(responseStudents);
			String location = ServletUriComponentsBuilder.fromCurrentRequest().path("/courses/{id}")
					.buildAndExpand(savedCourse.getId()).toUri().toString();
			return ResponseEntity.status(HttpStatus.CREATED).header("location", location).body(courseResponse);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CourseResponse> getCourse(@PathVariable String id) {
		Optional<Course> optionalCourse = courseService.getById(id);
		if (!optionalCourse.isEmpty()) {
			Course course = optionalCourse.get();
			CourseResponse courseResponse = new CourseResponse();
			InstituteResponse instituteResponse = new InstituteResponse();
			instituteResponse.setCourses(Arrays.asList(course.getId()));
			instituteResponse.setId(course.getInstitute().getId());
			instituteResponse.setMail(course.getInstitute().getMail());
			courseResponse.setCredits(course.getCredits());
			courseResponse.setInstitute(instituteResponse);
			courseResponse.setId(course.getId());
			courseResponse.setName(course.getName());
			courseResponse.setStudents(course.getStudents());
			return ResponseEntity.status(HttpStatus.OK).body(courseResponse);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@PutMapping("{id}")
	public ResponseEntity<CourseResponse> updateCourse(@PathVariable String id,
			@RequestBody CourseRequest courseRequest) {
		Optional<Course> optionalCourse = courseService.getById(id);
		if (optionalCourse.isEmpty() == true) {
			courseRequest.setId(id);
			return this.createCourse(courseRequest);
		} else {
			Course updatedCourse = optionalCourse.get();
			updatedCourse.setCredits(courseRequest.getCredits());
			updatedCourse.setName(courseRequest.getName());
			courseRequest.setId(id);
			Course savedCourse = courseService.save(updatedCourse);
			CourseResponse courseResponse = new CourseResponse();
			InstituteResponse instituteResponse = new InstituteResponse();
			instituteResponse.setCourses(Arrays.asList(savedCourse.getId()));
			instituteResponse.setId(savedCourse.getInstitute().getId());
			instituteResponse.setMail(savedCourse.getInstitute().getMail());
			courseResponse.setCredits(savedCourse.getCredits());
			courseResponse.setInstitute(instituteResponse);
			courseResponse.setId(savedCourse.getId());
			courseResponse.setName(savedCourse.getName());
			courseResponse.setStudents(savedCourse.getStudents());
			return ResponseEntity.status(HttpStatus.OK).body(courseResponse);
		}
	}

}
