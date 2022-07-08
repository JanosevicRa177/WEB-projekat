package services;

import java.util.Collection;
import java.util.Date;

import fileStorages.WorkoutHistoryFileStorage;
import model.Workout;
import model.WorkoutHistory;

public class WorkoutHistoryService {

	private static WorkoutHistoryFileStorage workoutHistoryFileStorage;
	
	public WorkoutHistoryService() {
		workoutHistoryFileStorage = new WorkoutHistoryFileStorage();
	}
	
	public String AddWorkoutHistory(Date date,int hours,Workout workout,String customerUsername) {
		return workoutHistoryFileStorage.AddWorkoutHistory(date,hours,workout,customerUsername);
	}
	
	public Collection<WorkoutHistory> GetWorkoutHistoryByCustomer(String customer) {
		return workoutHistoryFileStorage.GetWorkoutHistoryByCustomer(customer);
	}
	public Collection<WorkoutHistory> GetWorkoutHistoryByCoach(String coach) {
		return workoutHistoryFileStorage.GetWorkoutHistoryByCoach(coach);
	}
	public Collection<WorkoutHistory> GetWorkoutHistory() {
		return workoutHistoryFileStorage.GetWorkoutHistory();
	}
}
