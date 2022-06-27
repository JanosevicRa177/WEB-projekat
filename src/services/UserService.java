package services;

import enums.UserType;
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
	
	public boolean changeUser(User user) {
		if(user.getUserType() == UserType.Admin) {adminFileStorage.changeUser(user);return true;}
		if(user.getUserType() == UserType.Coach) {coachFileStorage.changeUser(user);return true;}
		if(user.getUserType() == UserType.Manager) {managerFileStorage.changeUser(user);return true;}
		if(user.getUserType() == UserType.Customer) {customerFileStorage.changeUser(user);return true;}
		return true;
	}
	
	public User getUser(String username) {
		Admin adm = adminFileStorage.readAdmins().get(username);
		if(adm != null) {
			return adm;
		}
		Manager man = managerFileStorage.readManagers().get(username);
		if(man != null) {
			return man;
		}
		Coach coa = coachFileStorage.readCoaches().get(username);
		if(coa != null) {
			return coa;
		}
		Customer cus = customerFileStorage.readCustomers().get(username);
		if(cus != null) {
			return cus;
		}
		return null;
	}
	
	public User findUser(String username, String password) {
		Admin adm = adminFileStorage.readAdmins().get(username);
		if(adm != null) {
			if (adm.getPassword().equals(password)) return adm;
			return null;
		}
		Manager man = managerFileStorage.readManagers().get(username);
		if(man != null) {
			if (man.getPassword().equals(password)) return man;
			return null;
		}
		Coach coa = coachFileStorage.readCoaches().get(username);
		if(coa != null) {
			if (coa.getPassword().equals(password)) return coa;
			return null;
		}
		Customer cus = customerFileStorage.readCustomers().get(username);
		if(cus != null) {
			if (cus.getPassword().equals(password)) return cus;
			return null;
		}
		return null;
	}
}
