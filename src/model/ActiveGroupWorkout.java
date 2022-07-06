package model;

import java.util.Date;

public class ActiveGroupWorkout {
	private Date checkinDate;
	private int hours;
	private String workout;
	private int id;
	
	public ActiveGroupWorkout(Date checkinDate, int hours, String workout, int id) {
		super();
		this.checkinDate = checkinDate;
		this.hours = hours;
		this.workout = workout;
		this.id = id;
	}
	public Date getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public String getWorkout() {
		return workout;
	}
	public void setWorkout(String workout) {
		this.workout = workout;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
