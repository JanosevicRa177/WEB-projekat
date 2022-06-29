package controller;

import static spark.Spark.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Customer;
import services.AdminService;
import services.CoachService;
import services.CustomerService;
import services.ManagerService;

public class CustomerController {
	
	public static Gson gson;
	private static CustomerService customerService;
	private static AdminService adminService;
	private static ManagerService managerService;
	private static CoachService coachService;
	
	public CustomerController() {
		customerService = new CustomerService();
		adminService = new AdminService();
		managerService = new ManagerService();
		coachService = new CoachService();
		gson = new GsonBuilder()
		        .setPrettyPrinting()
		        .setDateFormat("yyyy-MM-dd")
		        .create();
	}
	
	public static void addCustomer() {
		post("customer/add",(req, res) -> {
			res.type("application/json");
			Customer customer = gson.fromJson(req.body(), Customer.class);
			if(!customer.getName().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$") || !customer.getSurname().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$"))
				return "Name and surname should start with uppercase without numbers";
			if(!customerService.isUniqueUsername(customer.getUsername()) || !adminService.isUniqueUsername(customer.getUsername())
					|| !managerService.isUniqueUsername(customer.getUsername()) || !coachService.isUniqueUsername(customer.getUsername()))
				return "Username is not unique";
			customerService.addCustomer(customer);
			return "success";
		});
	}
	
}
