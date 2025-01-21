package de.rwth.swc.universitymanagement.dto;

public class StudentRequest {
	private String name;
	private String surname;
	private int credits;
	private int matriculationNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public int getMatriculationNumber() {
		return matriculationNumber;
	}
	public void setMatriculationNumber(int matriculationNumber) {
		this.matriculationNumber = matriculationNumber;
	}
	
	
}
