package services;

import java.util.ArrayList;
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
	
	public Manager getManager(String username) {
		return managerFileStorage.getManager(username);
	}
	
	
	public String addManager(Manager man) {
		if(!man.getSportBuilding().equals("None")) {
			sportBuildingFileStorage.setManager(man.getUsername(),man.getSportBuilding());
		}
		return managerFileStorage.addManager(man);
	}
	
	public Collection<Manager> GetAllManagers(){
		return managerFileStorage.readManagers().values();
	}
	
	public Collection<Manager> GetAllManagersNoSportBuilding(){
		Collection<Manager> mans = new  ArrayList<Manager>();
		for(Manager man : managerFileStorage.readManagers().values()) {
			if(man.getSportBuilding().equals("None")) {
				mans.add(man);
			}
		}
		return mans;
	}
	
	
	public String CheckSportBuilding(String managerUsername) {
		return managerFileStorage.CheckSportBuilding(managerUsername);
	}
}
