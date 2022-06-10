package model;

import java.time.LocalDate;

import enums.Gender;

public class Coach extends User{
	private WorkoutHistory workoutHistory;
	
	public Coach() {
		super();
	}

	public Coach(String username, String password, String name, String surname, Gender gender, LocalDate birthDate, WorkoutHistory workoutHistory) {
		super(username, password, name, surname, gender, birthDate);
		this.workoutHistory = workoutHistory;
	}

	public WorkoutHistory getWorkoutHistory() {
		return workoutHistory;
	}

	public void setWorkoutHistory(WorkoutHistory workoutHistory) {
		this.workoutHistory = workoutHistory;
	}
	
	
	
	
}
