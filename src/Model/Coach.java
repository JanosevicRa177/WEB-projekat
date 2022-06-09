package Model;

import java.time.LocalDate;

import Enums.Gender;
import Enums.Role;

public class Coach extends User{
	private WorkoutHistory workoutHistory;
	
	public Coach() {
		super();
	}

	public Coach(String username, String password, String name, String surname, Gender gender, LocalDate birthDate,
			Role role, WorkoutHistory workoutHistory) {
		super(username, password, name, surname, gender, birthDate, role);
		this.workoutHistory = workoutHistory;
	}

	public WorkoutHistory getWorkoutHistory() {
		return workoutHistory;
	}

	public void setWorkoutHistory(WorkoutHistory workoutHistory) {
		this.workoutHistory = workoutHistory;
	}
	
	
	
	
}
