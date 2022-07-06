package services;

import java.util.Date;

import fileStorages.ActiveGroupWorkoutFileStorage;
import model.Workout;

public class ActiveGroupWorkoutService {
	private static ActiveGroupWorkoutFileStorage activeGroupWorkoutFileStorage;
	
	public ActiveGroupWorkoutService() {
		activeGroupWorkoutFileStorage = new ActiveGroupWorkoutFileStorage();
	}
	
	public String AddActiveGroupWorkout(Date date,int hours,Workout workout) {
		return activeGroupWorkoutFileStorage.AddActiveGroupWorkout(date,hours,workout);
	}
}
