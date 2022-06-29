package services;

import java.util.Collection;

import fileStorages.ManagerFileStorage;
import model.Manager;

public class ManagerService {
	
	private static ManagerFileStorage managerFileStorage;
	
	public ManagerService() {
		managerFileStorage = new ManagerFileStorage();
	}
	
	public Boolean isUniqueUsername(String username) {
		return managerFileStorage.isUniqueUsername(username);
	}
	
	public Collection<Manager> GetAllManagers(){
		return managerFileStorage.readManagers().values();
	}
}
