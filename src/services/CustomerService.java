package services;

import fileStorages.CustomerFileStorage;
import model.Customer;
import model.User;

public class CustomerService {
	public static CustomerFileStorage customerFileStorage;
	public CustomerService() {
		customerFileStorage = new CustomerFileStorage();
	}
	public String addCustomer(Customer cus) {
			return customerFileStorage.addCustomer(cus);
	}
	
	public Customer loginCustomer(User u) {
		Customer cus = customerFileStorage.readCustomers().get(u.getUsername());
		if(cus == null) {
			return null;
		}
		else if (!cus.getPassword().equals(u.getPassword())) return null;
		return cus;
	}
	public Boolean isUniqueUsername(String username) {
		return customerFileStorage.isUniqueUsername(username);
	}
}
