package services;

import java.util.Collection;

import fileStorages.CoachFileStorage;
import model.Coach;

public class CoachService {
	
	private static CoachFileStorage coachFileStorage;
	
	public CoachService() {
		coachFileStorage = new CoachFileStorage();
	}
	
	public Boolean isUniqueUsername(String username) {
		return coachFileStorage.isUniqueUsername(username);
	}
	
	public String addCoach(Coach coach) {
		return coachFileStorage.addCoach(coach);
	}
	
	public Coach getCoach(String username) {
		return coachFileStorage.getCoach(username);
	}
	
	public Boolean IsValidCoach(String coachUsername) {
		return coachFileStorage.IsValidCoach(coachUsername);
	}
	
	public Collection<Coach> GetAllCoaches(){
		return coachFileStorage.readCoaches().values();
	}
}
