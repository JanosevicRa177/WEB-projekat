package services;

import fileStorages.AdminFileStorage;
import fileStorages.CoachFileStorage;
import fileStorages.CustomerFileStorage;
import fileStorages.ManagerFileStorage;
import model.Admin;
import model.Coach;
import model.Customer;
import model.Manager;
import model.User;

public class UserService {
	
	public static CustomerFileStorage customerFileStorage;
	public static AdminFileStorage adminFileStorage;
	public static ManagerFileStorage managerFileStorage;
	public static CoachFileStorage coachFileStorage;
	
	public UserService() {
		customerFileStorage = new CustomerFileStorage();
		adminFileStorage = new AdminFileStorage();
		managerFileStorage = new ManagerFileStorage();
		coachFileStorage = new CoachFileStorage();
	}
	
	public User loginUser(User u) {
		Admin adm = adminFileStorage.readAdmins().get(u.getUsername());
		if(adm != null) {
			if (adm.getPassword().equals(u.getPassword())) return adm;
			return null;
		}
		Manager man = managerFileStorage.readManagers().get(u.getUsername());
		if(man != null) {
			if (man.getPassword().equals(u.getPassword())) return man;
			return null;
		}
		Coach coa = coachFileStorage.readCoaches().get(u.getUsername());
		if(coa != null) {
			if (coa.getPassword().equals(u.getPassword())) return coa;
			return null;
		}
		Customer cus = customerFileStorage.readCustomers().get(u.getUsername());
		if(cus != null) {
			if (cus.getPassword().equals(u.getPassword())) return cus;
			return null;
		}
		return null;
	}
}
