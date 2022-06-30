package services;

import java.util.Collection;

import fileStorages.ManagerFileStorage;
import fileStorages.SportBuildingFileStorage;
import model.Manager;

public class ManagerService {
	
	private static ManagerFileStorage managerFileStorage;
	private static SportBuildingFileStorage sportBuildingFileStorage;
	
	public ManagerService() {
		managerFileStorage = new ManagerFileStorage();
		sportBuildingFileStorage = new SportBuildingFileStorage();
	}
	
	public Boolean isUniqueUsername(String username) {
		return managerFileStorage.isUniqueUsername(username);
	}
	
	public String addManager(Manager man) {
		sportBuildingFileStorage.setManager(man.getUsername(),man.getSportBuilding());
		return managerFileStorage.addManager(man);
	}
	
	public Collection<Manager> GetAllManagers(){
		return managerFileStorage.readManagers().values();
	}
}
