package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Customer;
import services.CustomerService;

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
			Customer cus = gson.fromJson(req.body(), Customer.class);
			System.out.println(cus.getBirthDate());
			return customerService.addCustomer(cus);
		});
	}
	public static void getCustomer() {
		get("customer/get", (req, res) -> {
			res.type("application/json");
			return "SUCCESS";
		});
	}
}
