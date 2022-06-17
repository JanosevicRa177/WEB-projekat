package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Customer;
import model.User;
import services.CustomerService;
import spark.Session;

public class CustomerController {
	public static Gson gson;
	private static CustomerService customerService;
	public CustomerController() {
		customerService = new CustomerService();
		gson = new GsonBuilder()
		        .setPrettyPrinting()
		        .setDateFormat("yyyy-MM-dd")
		        .create();
	}
	
	public static void addCustomer() {
		post("customer/add",(req, res) -> {
			res.type("application/json");
			System.out.println(req.body());
			Customer customer = gson.fromJson(req.body(), Customer.class);
			if(!customer.getName().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$") || !customer.getSurname().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$"))
				return "Name and surname should start with uppercase without numbers";
			if(!customerService.isUniqueUsername(customer.getUsername()))
				return "Username is not unique";
			return customerService.addCustomer(customer);
		});
	}
	public static void getCustomer() {
		get("customer/get", (req, res) -> {
			res.type("application/json");
			return "SUCCESS";
		});
	}
	
	public static void getLogged() {
		get("customer/getlogged", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			Customer loggedcustomer = ss.attribute("customer");
			if(loggedcustomer != null) 
			return true;
			return false;
		});
	}
	
	public static void logOff() {
		get("customer/logoff", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			Customer loggedcustomer = ss.attribute("customer");
			if(loggedcustomer != null) 
				ss.invalidate();
			return "success";
		});
	}

	public static void Login() {
		post("customer/login",(req, res) -> {
			res.type("application/json");
			User ut = gson.fromJson(req.body(), User.class);
			Session ss = req.session(true);
			Customer loggedcustomer = ss.attribute("customer");
			if (loggedcustomer == null) {
				Customer cus = customerService.loginCustomer(ut);
				if(cus != null) {
					loggedcustomer = cus;
					ss.attribute("customer", cus);
					return "logged";
				}
				return "wrong";
			}
			return loggedcustomer.getUsername();
		});
	}
}
