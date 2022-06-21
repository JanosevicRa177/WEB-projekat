package model;

import java.util.Date;

import enums.Gender;
import enums.UserType;

public class Admin extends User{

	public Admin() 
	{
		super();
	}
	
	public Admin(String username, String password, String name, String surname, Gender gender, Date birthDate) {
		super(username, password, name, surname, gender, birthDate,UserType.Admin);
	}
	
	public Admin change(User us) {
		this.setPassword(us.getPassword());
		this.setName(us.getName());
		this.setSurname(us.getSurname());
		this.setGender(us.getGender());
		this.setBirthDate(us.getBirthDate());
		return this;
	}
}
