package services;

import java.util.Collection;

import fileStorages.CustomerFileStorage;
import model.Customer;

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
	public Collection<Customer> GetAllCustomers(){
		return customerFileStorage.readCustomers().values();
	}
}
