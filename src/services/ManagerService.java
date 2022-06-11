package services;

import fileStorages.ManagerFileStorage;

public class ManagerService {
	private static ManagerFileStorage managerFileStorage;
	public ManagerService() {
		managerFileStorage = new ManagerFileStorage();
	}
}
