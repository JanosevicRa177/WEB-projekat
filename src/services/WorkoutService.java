package services;

import java.util.ArrayList;
import java.util.Collection;

import fileStorages.CoachFileStorage;

import fileStorages.WorkoutFileStorage;
import model.Coach;
import model.Workout;

public class WorkoutService {
	private static WorkoutFileStorage workoutFileStorage;
	private static CoachFileStorage coachFileStorage;
	
	public WorkoutService() {
		workoutFileStorage = new WorkoutFileStorage();
		coachFileStorage = new CoachFileStorage();
	}
	
	public Boolean IsUniqueName(String name) {
		return workoutFileStorage.IsUniqueName(name);
	}
	
	public Collection<Coach> getAllCoachesForSportBuilding(String sportBuilding){
		Collection<Coach> coaches = new  ArrayList<Coach>();
		for(Workout work : workoutFileStorage.readWorkouts().values()) {
			if(work.getSportBuildingName().equals(sportBuilding)) {
				Coach coach = coachFileStorage.readCoaches().get(work.getCoachUsername());
				if(!coaches.contains(coach) && coach!=null) 
					coaches.add(coach);
			}
		}
		return coaches;
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
	
	public Collection<Workout> GetWorkoutsByCoach(String coach) {
		return workoutFileStorage.GetWorkoutsByCoach(coach);
	}
	
	public Collection<Workout> GetPersonalWorkouts() {
		return workoutFileStorage.GetPersonalWorkouts();
	}
	public Workout GetWorkoutsByName(String workoutName) {
		return workoutFileStorage.GetWorkoutsByName(workoutName);
	}
}
