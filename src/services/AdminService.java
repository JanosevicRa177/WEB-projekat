package services;

import fileStorages.AdminFileStorage;

public class AdminService {
private static AdminFileStorage adminFileStorage;
	public AdminService() {
		adminFileStorage = new AdminFileStorage();
	}
}
