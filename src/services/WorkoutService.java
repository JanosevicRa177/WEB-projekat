package services;

import fileStorages.WorkoutFileStorage;
import model.Workout;

public class WorkoutService {
	private static WorkoutFileStorage workoutFileStorage;
	
	public WorkoutService() {
		workoutFileStorage = new WorkoutFileStorage();
	}
	
	public Boolean IsUniqueName(String name) {
		return workoutFileStorage.isUniqueName(name);
	}
	
	public String addWorkout(Workout workout) {
		return workoutFileStorage.addWorkout(workout);
	}
}
