package de.rwth.swc.universitymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import de.rwth.swc.universitymanagement.model.Student;
import de.rwth.swc.universitymanagement.model.Course;

public interface StudentRepository extends CrudRepository<Student, Integer> {
	@Query("SELECT s.course FROM Student s WHERE s.id = :studentId")
	Course findCourseByStudentId(@Param("studentId") int studentId);
	
	 List<Student> findByCourseId(String courseId);
}
