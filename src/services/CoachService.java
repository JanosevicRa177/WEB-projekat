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
	
	public String addCoach(Coach coa) {
		return coachFileStorage.addCoach(coa);
	}
	
	public Collection<Coach> GetAllCoaches(){
		return coachFileStorage.readCoaches().values();
	}
}
