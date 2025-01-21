package de.rwth.swc.universitymanagement.dto;

import java.util.List;

public class InstituteResponse {
	private String id;
	
	private String mail;
	
	private List<String> courses;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}
	
	
}
