package de.rwth.swc.universitymanagement.repository;

import org.springframework.data.repository.CrudRepository;

import de.rwth.swc.universitymanagement.model.Course;

public interface CourseRepository extends CrudRepository<Course, String>{

}
