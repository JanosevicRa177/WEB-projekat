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
			customerService.addCustomer(customer); ///// ova linija ne znam da li je bila tu xd idk xd proveri mislim da jeste ali sam je slucajno izbrisao xd
			return "success";//return customerService.addCustomer(customer); ///// OVO SAM STAVIO DA ZNAM DA GA PREUSMERIM NA LOGIN STRANICU AKO JE SVE OKEJ XD JER SVAKAKO NE KORSITIS USERA XD
		});
	}
	public static void getCustomer() {
		get("customer/get", (req, res) -> {
			res.type("application/json");
			return "SUCCESS";
		});
	}
	
	
}
