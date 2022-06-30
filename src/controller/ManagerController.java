package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.UserType;
import model.Manager;
import model.User;
import services.ManagerService;
import spark.Session;

public class ManagerController {
	public static Gson gson;
	private static ManagerService managerService;
	public ManagerController() {
		managerService = new ManagerService();
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	}
	public static void getManager() {
		get("customer/get", (req, res) -> {
			res.type("application/json");
			return "SUCCESS";
		});
	}
	
	public static void addManager() {
		post("manager/add",(req, res) -> {
			res.type("application/json");
			Manager manager = gson.fromJson(req.body(), Manager.class);
			if(!manager.getName().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$") || !manager.getSurname().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$"))
				return "Name and surname should start with uppercase without numbers";
			if(!managerService.isUniqueUsername(manager.getUsername()) || !managerService.isUniqueUsername(manager.getUsername())
					|| !managerService.isUniqueUsername(manager.getUsername()) || !managerService.isUniqueUsername(manager.getUsername()))
				return "Username is not unique";
			return managerService.addManager(manager);
		});
	}
	public static void GetAllManagers() {
		get("manager/getAll",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user.getUserType() == UserType.Admin)
				return gson.toJson(managerService.GetAllManagers());
			else return null;
		});
	}
}
