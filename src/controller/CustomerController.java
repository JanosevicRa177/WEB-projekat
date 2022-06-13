package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.FieldNamingPolicy;
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
		        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
		        .create();;
	}
	
	public static void addCustomer() {
		post("customer/add",(req, res) -> {
			Customer cus = gson.fromJson(req.body(), Customer.class);
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
