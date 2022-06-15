package services;

import java.util.Collection;

import fileStorages.SportBuildingFileStorage;
import model.SportBuilding;

public class SportBuildingService {
	public static SportBuildingFileStorage sportBuildingFileStorage;
	public SportBuildingService() {
		sportBuildingFileStorage = new SportBuildingFileStorage();
	}
	public Collection<SportBuilding> getSportBuildings(){
		return sportBuildingFileStorage.getSportBuildings();
	}
}
