package model;

import java.time.LocalDate;

import enums.Gender;

public class Manager extends User {
	
	private String sportBuilding;

	public Manager(String username, String password, String name, String surname, Gender gender, LocalDate birthDate,String sportBuilding) {
		super(username, password, name, surname, gender, birthDate);
		this.sportBuilding = sportBuilding;
	}

	public String getSportBuilding() {
		return sportBuilding;
	}

	public void setSportBuilding(String sportBuilding) {
		this.sportBuilding = sportBuilding;;
	}
	
}
