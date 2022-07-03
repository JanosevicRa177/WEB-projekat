package services;

import java.util.Collection;

import fileStorages.ManagerFileStorage;
import fileStorages.SportBuildingFileStorage;
import model.SportBuilding;

public class SportBuildingService {
	public static SportBuildingFileStorage sportBuildingFileStorage;
	public static ManagerFileStorage managerFileStorage;
	public SportBuildingService() {
		sportBuildingFileStorage = new SportBuildingFileStorage();
		managerFileStorage = new ManagerFileStorage();
	}
	public Collection<SportBuilding> getSportBuildings(){
		return sportBuildingFileStorage.getSportBuildings();
	}
	public SportBuilding GetSportBuilding(String sportBuildingName){
		return sportBuildingFileStorage.GetSportBuilding(sportBuildingName);
	}
	
	public Collection<SportBuilding> getSportBuildingsNoManager(){
		return sportBuildingFileStorage.getSportBuildingsNoManager();
	}
	
	public boolean isNameUnique(String name) {
		return sportBuildingFileStorage.isNameUnique(name);
	}
	
	public boolean addSportBuilding(SportBuilding spB) {
		managerFileStorage.setSportBuilding(spB.getManager(), spB.getName());
		return sportBuildingFileStorage.addSportBuilding(spB);
	}
	
	public String GetSportBuildingNameByManager(String managerName) {
		return sportBuildingFileStorage.GetSportBuildingNameByManager(managerName);
	}
	
}
