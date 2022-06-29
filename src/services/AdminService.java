package services;

import java.util.Collection;

import fileStorages.AdminFileStorage;
import model.Admin;

public class AdminService {
	
private static AdminFileStorage adminFileStorage;

	public AdminService() {
		adminFileStorage = new AdminFileStorage();
	}
	
	public Boolean isUniqueUsername(String username) {
		return adminFileStorage.isUniqueUsername(username);
	}
	
	public Collection<Admin> GetAllAdmins(){
		return adminFileStorage.readAdmins().values();
	}
}
