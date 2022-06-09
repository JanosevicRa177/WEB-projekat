package Model;

import java.time.LocalDate;

import Enums.Gender;
import Enums.Role;

public class Manager extends User {
	
	private String sportBuilding;

	public Manager(String username, String password, String name, String surname, Gender gender, LocalDate birthDate,
			Role role,String sportBuilding) {
		super(username, password, name, surname, gender, birthDate, role);
		this.sportBuilding = sportBuilding;
	}

	public String getSportBuilding() {
		return sportBuilding;
	}

	public void setSportBuilding(String sportBuilding) {
		this.sportBuilding = sportBuilding;
	}
	
}
