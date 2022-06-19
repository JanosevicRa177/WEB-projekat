package services;

import fileStorages.CoachFileStorage;

public class CoachService {
	
	private static CoachFileStorage coachFileStorage;
	
	public CoachService() {
		coachFileStorage = new CoachFileStorage();
	}
	
	public Boolean isUniqueUsername(String username) {
		return coachFileStorage.isUniqueUsername(username);
	}
	
}
