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
	
	
	public Boolean isUniqueUsername(String username) {
		return customerFileStorage.isUniqueUsername(username);
	}
}
