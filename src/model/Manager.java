package model;

import java.util.Date;

import enums.Gender;
import enums.UserType;

public class Manager extends User {
	
	private String sportBuilding;

	public Manager(String username, String password, String name, String surname, Gender gender, Date birthDate) {
		super(username, password, name, surname, gender, birthDate,UserType.Manager);
	}

	public String getSportBuilding() {
		return sportBuilding;
	}

	public void setSportBuilding(String sportBuilding) {
		this.sportBuilding = sportBuilding;;
	}
	
	public Manager change(User us) {
		if(!us.getPassword().equals("")) this.setPassword(us.getPassword());
		this.setName(us.getName());
		this.setSurname(us.getSurname());
		this.setGender(us.getGender());
		this.setBirthDate(us.getBirthDate());
		return this;
	}
	
}
