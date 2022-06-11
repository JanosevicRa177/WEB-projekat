package services;

import fileStorages.CustomerFileStorage;

public class CustomerService {
	public static CustomerFileStorage customerFileStorage;
	public CustomerService() {
		customerFileStorage = new CustomerFileStorage();
	}
	public String addCustomer() {
			return customerFileStorage.addCustomer();
	}
}
