package model;

import java.time.LocalDate;

public class WorkoutHistory {
	private LocalDate checkinDate;
	private Workout workout;
	private Customer customer;
	private Coach coach;
	
	public WorkoutHistory() {
		super();
	}

	public WorkoutHistory(LocalDate checkinDate, Workout workout, Customer customer, Coach coach) {
		super();
		this.checkinDate = checkinDate;
		this.workout = workout;
		this.customer = customer;
		this.coach = coach;
	}

	public LocalDate getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(LocalDate checkinDate) {
		this.checkinDate = checkinDate;
	}

	public Workout getWorkout() {
		return workout;
	}

	public void setWorkout(Workout workout) {
		this.workout = workout;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	
	
	
	
}
