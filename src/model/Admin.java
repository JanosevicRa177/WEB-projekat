package model;

import java.util.Date;

import enums.Gender;

public class Admin extends User{

	public Admin() 
	{
		super();
	}
	
	public Admin(String username, String password, String name, String surname, Gender gender, Date birthDate) {
		super(username, password, name, surname, gender, birthDate);
	}
}
