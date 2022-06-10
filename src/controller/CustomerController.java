package controller;

import static spark.Spark.post;
import static spark.Spark.get;

import com.google.gson.Gson;

import model.Customer;

public class CustomerController {
	private static Gson g = new Gson();
	public CustomerController() {}
	
	public static void addCustomer() {
		post("customer/add", (req, res) -> {
			res.type("application/json");
			Customer pd = g.fromJson(req.body(), Customer.class);
			return "SUCCESS";
		});
	}
	public static void getCustomer() {
		get("customer/get", (req, res) -> {
			res.type("application/json");
			return "SUCCESS";
		});
	}
}
