package controller;

import static spark.Spark.get;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.UserType;
import model.User;
import services.AdminService;
import spark.Session;

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
	
	public static void GetAllAdmins() {
		get("admin/getAll",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user.getUserType() == UserType.Admin)
				return gson.toJson(adminService.GetAllAdmins());
			else return gson.toJson(null);
		});
	}
}
