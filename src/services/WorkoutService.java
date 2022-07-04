package services;

import java.util.Collection;

import fileStorages.WorkoutFileStorage;
import model.Workout;

public class WorkoutService {
	private static WorkoutFileStorage workoutFileStorage;
	
	public WorkoutService() {
		workoutFileStorage = new WorkoutFileStorage();
	}
	
	public Boolean IsUniqueName(String name) {
		return workoutFileStorage.IsUniqueName(name);
	}
	
	public String addWorkout(Workout workout) {
		return workoutFileStorage.AddWorkout(workout);
	}
	public String ChangeWorkout(Workout workout,String oldWorkoutName) {
		return workoutFileStorage.ChangeWorkout(workout, oldWorkoutName);
	}
	public Collection<Workout> GetWorkoutsBySportBuilding(String sportBuilding) {
		return workoutFileStorage.GetWorkoutsBySportBuilding(sportBuilding);
	}
	public Workout GetWorkoutsByName(String workoutName) {
		return workoutFileStorage.GetWorkoutsByName(workoutName);
	}
}
