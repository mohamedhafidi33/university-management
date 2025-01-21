package de.rwth.swc.universitymanagement.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.rwth.swc.universitymanagement.model.Course;
import de.rwth.swc.universitymanagement.model.Student;
import de.rwth.swc.universitymanagement.service.CourseService;
import de.rwth.swc.universitymanagement.service.StudentService;

/**
 * @author Mohamed Hafidi
 */

@RestController
@RequestMapping("/students")
public class StudentsController {
	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student savedStudent = studentService.save(student);
		String location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri().toString();
		return ResponseEntity.status(HttpStatus.CREATED).header("location", location).body(savedStudent);
	}

	@GetMapping("/{id}")
	public Student getStudent(@PathVariable int id) {
		Optional<Student> optionalStudent = studentService.getById(id);
		if (optionalStudent.isPresent()) {
			return optionalStudent.get();
		}
		return null;
	}

	@PostMapping("/{studentId}/courses/register/{courseId}")
	public ResponseEntity<String> registerCourse(@PathVariable int studentId, @PathVariable String courseId) {
		Optional<Student> optionalStudent = studentService.getById(studentId);
		Optional<Course> optionalCourse = courseService.getById(courseId);
		System.out.println("Registering student-----");
		if (!optionalCourse.isEmpty() && !optionalStudent.isEmpty()) {
			Course course = optionalCourse.get();
			Student student = optionalStudent.get();
			courseService.registerStudent(student, course);
			System.out.println("Student registed------");
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return null;
	}

	@GetMapping("/{studentId}/courses")
	public ResponseEntity<List<Course>> getCourses(@PathVariable int studentId) {
		System.out.println("looking for course-----");
		Course course = studentService.getCourseByStudentId(studentId);
		System.out.println("Course found------");
		List<Course> courses = course != null ? Arrays.asList(course) : Arrays.asList();
		return ResponseEntity.status(HttpStatus.OK).body(courses);
	}

	@DeleteMapping("/{studentId}/courses/{courseId}")
	public ResponseEntity<Student> unsubscribeFromCourse(@PathVariable int studentId, @PathVariable String courseId) {
		Optional<Student> optionalStudent = studentService.getById(studentId);
		Optional<Course> optionalCourse = courseService.getById(courseId);
		System.out.println("unsubscribing from course-----");
		if (!optionalCourse.isEmpty() && !optionalStudent.isEmpty()) {
			Course course = optionalCourse.get();
			Student student = optionalStudent.get();
			studentService.unsubscribeFromCourse(student, course);
			System.out.println("Student unsubscribed------");
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
