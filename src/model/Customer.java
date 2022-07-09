package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import enums.CustomerType;
import enums.Gender;
import enums.UserType;

public class Customer extends User{
	
	private Membership membership;
	private CustomerType customerType;
	private List<SportBuilding> visitedBuildings;
	private double points;
	
	public Customer() {
		super();
	}
	
	public Customer(String username, String password, String name, String surname, Gender gender, Date birthDate) {
		super(username, password, name, surname, gender, birthDate,UserType.Customer);
		initCustomer();
	}
	public void initCustomer() {
		this.membership = new Membership();
		this.visitedBuildings = new ArrayList<SportBuilding>();
		this.points = 0.0;
	}
	
	public Customer change(User us) {
		if(!us.getPassword().equals("")) this.setPassword(us.getPassword());
		this.setName(us.getName());
		this.setSurname(us.getSurname());
		this.setGender(us.getGender());
		this.setBirthDate(us.getBirthDate());
		return this;
	}
	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
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

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

}
