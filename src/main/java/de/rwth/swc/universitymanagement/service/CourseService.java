package de.rwth.swc.universitymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.rwth.swc.universitymanagement.model.Course;
import de.rwth.swc.universitymanagement.model.Student;
import de.rwth.swc.universitymanagement.repository.CourseRepository;
import de.rwth.swc.universitymanagement.repository.StudentRepository;

/**
 * @author Mohamed Hafidi
 */

@Service
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	StudentRepository studentRepository;
	
	public Course save(Course course) {
		return courseRepository.save(course);
	}

	public Optional<Course> getById(String id) {
		return courseRepository.findById(id);
	}
	
	public void registerStudent(Student student, Course course) {
		student.setCourse(course);
		course.getStudents().add(student);
		studentRepository.save(student);
		courseRepository.save(course);
	}
	
	public List<Student> getStudentsByCourse(String id) {
		return studentRepository.findByCourseId(id);
	}
}
