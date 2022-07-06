package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.UserType;
import model.Coach;
import model.User;
import services.AdminService;
import services.CoachService;
import services.CustomerService;
import services.ManagerService;
import spark.Session;

public class CoachController {
	public static Gson gson;
	private static CoachService coachService;
	private static CustomerService customerService;
	private static ManagerService managerService;
	private static AdminService adminService;
	public CoachController() {
		coachService = new CoachService();
		customerService = new CustomerService();
		managerService = new ManagerService();
		adminService = new AdminService();
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	}
	
	public static void getCoach() {
		get("customer/get", (req, res) -> {
			res.type("application/json");
			return "SUCCESS";
		});
	}
	
	public static void addCoach() {
		post("coach/add",(req, res) -> {
			res.type("application/json");
			Coach coach = gson.fromJson(req.body(), Coach.class);
			if(!coach.getName().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$") || !coach.getSurname().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$"))
				return "Name and surname should start with uppercase without numbers";
			if(!coachService.isUniqueUsername(coach.getUsername()) || !adminService.isUniqueUsername(coach.getUsername())
					|| !managerService.isUniqueUsername(coach.getUsername()) || !customerService.isUniqueUsername(coach.getUsername()))
				return "Username is not unique";
			return coachService.addCoach(coach);
		});
	}
	
	public static void GetAllCoaches() {
		get("coach/getAll",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user.getUserType() == UserType.Admin || user.getUserType() == UserType.Manager)
				return gson.toJson(coachService.GetAllCoaches());
			else return null;
		});
	}
}
