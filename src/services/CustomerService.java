package services;

import java.util.ArrayList;
import java.util.Collection;

import fileStorages.CustomerFileStorage;
import fileStorages.WorkoutHistoryFileStorage;
import model.Coach;
import model.Customer;
import model.WorkoutHistory;

public class CustomerService {
	
	public static CustomerFileStorage customerFileStorage;
	public static WorkoutService workoutService;
	public static CoachService coachService;
	public static WorkoutHistoryFileStorage workoutHistoryFileStorage;
	
	public CustomerService() {
		customerFileStorage = new CustomerFileStorage();
		workoutService = new WorkoutService();
		workoutHistoryFileStorage = new WorkoutHistoryFileStorage();
		coachService = new CoachService();
	}
	
	public String addCustomer(Customer cus) {
			return customerFileStorage.addCustomer(cus);
	}
	
	public Collection<Customer> getCustomersSportBuilding(String sportBuilding){
		Collection<Customer> customers = new ArrayList<Customer>();
		Collection<Coach> coaches = workoutService.getAllCoachesForSportBuilding(sportBuilding);
		boolean bol=true;
		for(WorkoutHistory WH : workoutHistoryFileStorage.getAllWorkoutHistories()) {
			bol = true;
			for(Coach co : coaches) {
				if(co.getUsername().equals(WH.getCoach())) {
					for(Customer cus : customers) {
						if(cus.getUsername().equals(WH.getCustomer())) {
							bol = false;
							break;
						}
					}
					if(bol) customers.add(this.getCustomer(WH.getCustomer()));
				}
			}
		}
		return customers;
	}
	
	public Customer getCustomer(String username) {
		return customerFileStorage.getCustomer(username);
	}
	
	public Boolean isUniqueUsername(String username) {
		return customerFileStorage.isUniqueUsername(username);
	}
	public Collection<Customer> GetAllCustomers(){
		return customerFileStorage.readCustomers().values();
	}
}
