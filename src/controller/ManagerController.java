package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.UserType;
import model.Manager;
import model.User;
import services.AdminService;
import services.CoachService;
import services.CustomerService;
import services.ManagerService;
import spark.Session;

public class ManagerController {
	public static Gson gson;
	private static CoachService coachService;
	private static CustomerService customerService;
	private static ManagerService managerService;
	private static AdminService adminService;
	
	public ManagerController() {
		coachService = new CoachService();
		customerService = new CustomerService();
		managerService = new ManagerService();
		adminService = new AdminService();
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	}
	public static void getManager() {
		get("customer/get", (req, res) -> {
			res.type("application/json");
			return "SUCCESS";
		});
	}
	
	public static void getManagersSportBuilding() {
		get("manager/getManagersSportBuilding", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			return managerService.getManager(user.getUsername()).getSportBuilding();
		});
	}
	
	public static void addManager() {
		post("manager/add",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Admin)
				return "You are not admin!";
			Manager manager = gson.fromJson(req.body(), Manager.class);
			if(!manager.getName().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$") || !manager.getSurname().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$"))
				return "Name and surname should start with uppercase without numbers";
			if(!coachService.isUniqueUsername(manager.getUsername()) || !adminService.isUniqueUsername(manager.getUsername())
					|| !managerService.isUniqueUsername(manager.getUsername()) || !customerService.isUniqueUsername(manager.getUsername()))
				return "Username is not unique";
			return managerService.addManager(manager);
		});
	}
	public static void GetAllManagers() {
		get("manager/getAll",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user.getUserType() == UserType.Admin) {
				Collection<Manager> cMan = managerService.GetAllManagers();
				if(cMan.size() == 0) return gson.toJson(null);
				return gson.toJson(managerService.GetAllManagers());
			}
			else return gson.toJson(null);
		});
	}
	
	public static void getAllNoSportBuilding() {
		get("manager/getAllNoSportBuilding",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user.getUserType() == UserType.Admin) {
				Collection<Manager> cMan = managerService.GetAllManagersNoSportBuilding();
				if(cMan.size() == 0) return gson.toJson(null);
				return gson.toJson(managerService.GetAllManagersNoSportBuilding());
			}
			else return gson.toJson(null);
		});
	}
	
	

	public static void CheckSportBuilding() {
		get("manager/checkSportBuilding",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Manager)
				return "You are not manager!";
			return managerService.CheckSportBuilding(user.getUsername());
		});
	}
}
