package controller;

import static spark.Spark.get;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import services.AdminService;

public class AdminController {
	public static Gson gson;
	private static AdminService adminService;
	public AdminController() {
		adminService = new AdminService();
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	}
	public static void getAdmin() {
		get("customer/get", (req, res) -> {
			res.type("application/json");
			return "SUCCESS";
		});
	}
}
