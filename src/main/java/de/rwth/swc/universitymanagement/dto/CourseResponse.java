package de.rwth.swc.universitymanagement.dto;

import java.util.List;

import de.rwth.swc.universitymanagement.model.Student;

public class CourseResponse {
	private String id;
	private String name;
	private int credits;
	private List<Student> students;
	private InstituteResponse institute;

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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public InstituteResponse getInstitute() {
		return institute;
	}

	public void setInstitute(InstituteResponse institute) {
		this.institute = institute;
	}
	
}
