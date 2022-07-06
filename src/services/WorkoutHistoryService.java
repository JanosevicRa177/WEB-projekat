package services;

import java.util.Date;

import fileStorages.WorkoutHistoryFileStorage;
import model.Workout;

public class WorkoutHistoryService {

	private static WorkoutHistoryFileStorage workoutHistoryFileStorage;
	
	public WorkoutHistoryService() {
		workoutHistoryFileStorage = new WorkoutHistoryFileStorage();
	}
	
	public String AddWorkoutHistory(Date date,int hours,Workout workout,String customerUsername) {
		return workoutHistoryFileStorage.AddWorkoutHistory(date,hours,workout,customerUsername);
	}
}
