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
	
	public boolean changeUser(User us) {
		if(us.getUserType() == UserType.Admin) {adminFileStorage.changeUser(us);return true;}
		if(us.getUserType() == UserType.Coach) {coachFileStorage.changeUser(us);return true;}
		if(us.getUserType() == UserType.Manager) {managerFileStorage.changeUser(us);return true;}
		if(us.getUserType() == UserType.Customer) {customerFileStorage.changeUser(us);return true;}
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
	
	public User findUser(User u) {
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
