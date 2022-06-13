package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.Gender;

public class Customer extends User{
	
	private Membership membership;
	private List<SportBuilding> visitedBuildings;
	private int points;
	
	public Customer() {
		super();
	}
	
	public Customer(String username, String password, String name, String surname, Gender gender, LocalDate birthDate) {
		super(username, password, name, surname, gender, birthDate);
		initCustomer();
	}
	public void initCustomer() {
		this.membership = new Membership();
		this.visitedBuildings = new ArrayList<SportBuilding>();
		this.points = 0;
	}
	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
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
