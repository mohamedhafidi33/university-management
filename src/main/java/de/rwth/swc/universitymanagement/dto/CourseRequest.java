package de.rwth.swc.universitymanagement.dto;

import java.util.List;

import de.rwth.swc.universitymanagement.model.Student;

public class CourseRequest {
	private String id;
	private String name;
	private int credits;
	private String instituteId;
	private List<Student> students;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

}
