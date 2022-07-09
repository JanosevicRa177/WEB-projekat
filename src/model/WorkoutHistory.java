package model;

import java.util.Date;

public class WorkoutHistory {
	private Date checkinDate;
	private int hours;
	private String workout;
	private String customer;
	private String coach;
	private int id;
	private int isDeclined;

	public WorkoutHistory() {
		super();
	}

	public WorkoutHistory(Date checkinDate, String workout, String customer, String coach,int id,int hours) {
		super();
		this.hours = hours;
		this.checkinDate = checkinDate;
		this.workout = workout;
		this.customer = customer;
		this.coach = coach;
		this.id = id;
		this.isDeclined = 0;
	}
	
	public int getIsDeclined() {
		return isDeclined;
	}

	public void setIsDeclined(int isDeclined) {
		this.isDeclined = isDeclined;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public String getWorkout() {
		return workout;
	}

	public void setWorkout(String workout) {
		this.workout = workout;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}
	
	
	
	
}
