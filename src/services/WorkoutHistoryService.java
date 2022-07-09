package services;

import java.util.Collection;
import java.util.Date;

import fileStorages.MembershipFileSotrage;
import fileStorages.WorkoutHistoryFileStorage;
import model.Workout;
import model.WorkoutHistory;

public class WorkoutHistoryService {

	private static WorkoutHistoryFileStorage workoutHistoryFileStorage;
	private static MembershipFileSotrage membershipFileStorage;
	
	public WorkoutHistoryService() {
		workoutHistoryFileStorage = new WorkoutHistoryFileStorage();
		membershipFileStorage = new MembershipFileSotrage();
	}
	
	public String AddWorkoutHistory(Date date,int hours,Workout workout,String customerUsername) {
		if(membershipFileStorage.getCustomersMembership(customerUsername).getVisitedSportArena() >=  
				membershipFileStorage.getCustomersMembership(customerUsername).getWorkoutNumber()) {
			return "You cannot go to trainings anymore";
		}
			membershipFileStorage.increase(customerUsername);
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
	public String CancelWorkout(int id) {
		return workoutHistoryFileStorage.CancelWorkout(id);
	}
}
