package Model;

import java.time.LocalDate;
import java.util.List;

import Enums.Gender;
import Enums.Role;

public class Customer extends User{
	
	private Dues dues;
	private List<SportBuilding> visitedBuildings;
	private int points;
	
	public Customer() {
		super();
	}

	public Customer(String username, String password, String name, String surname, Gender gender, LocalDate birthDate,
			Role role, Dues dues, List<SportBuilding> visitedBuildings, int points) {
		super(username, password, name, surname, gender, birthDate, role);
		this.dues = dues;
		this.visitedBuildings = visitedBuildings;
		this.points = points;
	}

	public Dues getDues() {
		return dues;
	}

	public void setDues(Dues dues) {
		this.dues = dues;
	}

	public List<SportBuilding> getVisitedBuildings() {
		return visitedBuildings;
	}

	public void setVisitedBuildings(List<SportBuilding> visitedBuildings) {
		this.visitedBuildings = visitedBuildings;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
	
}
