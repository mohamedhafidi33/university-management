package de.rwth.swc.universitymanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.rwth.swc.universitymanagement.model.*;
import de.rwth.swc.universitymanagement.repository.StudentRepository;

/**
 * @author Mohamed Hafidi
 */

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;

	public Student save(Student student) {
		return studentRepository.save(student);
	}

	public Optional<Student> getById(int id) {
		return studentRepository.findById(id);
	}

	public Course getCourseByStudentId(int studentId) {
		Course course = studentRepository.findCourseByStudentId(studentId);
		return course;
	}

	public void unsubscribeFromCourse(Student student, Course course) {
		student.setCourse(null);
		studentRepository.save(student);
	}
}
