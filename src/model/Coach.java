package model;

import java.util.Date;

import enums.Gender;
import enums.UserType;

public class Coach extends User{
	private WorkoutHistory workoutHistory;
	
	public Coach() {
		super();
	}

	public Coach(String username, String password, String name, String surname, Gender gender, Date birthDate) {
		super(username, password, name, surname, gender, birthDate,UserType.Coach);
	}

	public WorkoutHistory getWorkoutHistory() {
		return workoutHistory;
	}

	public void setWorkoutHistory(WorkoutHistory workoutHistory) {
		this.workoutHistory = workoutHistory;
	}
	
	
	
	
}
