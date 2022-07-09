package controller;

import static spark.Spark.get;
import static spark.Spark.put;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.UserType;
import fileStorages.CustomerFileStorage;
import model.User;
import services.MembershipService;
import services.UserService;
import spark.Session;

public class UserController {
	
	public static Gson gson;
	
	private static UserService userService;
	private static MembershipService  membershipService;
	private static CustomerFileStorage customerFileStorage;
	
	public UserController() {
		userService = new UserService();
		customerFileStorage = new CustomerFileStorage();
		membershipService = new MembershipService();
		gson = new GsonBuilder()
		        .setPrettyPrinting()
		        .setDateFormat("yyyy-MM-dd")
		        .create();
	}
	
	public static void ChangeUser() {
		put("user/changeUser",(req, res) -> {
			res.type("application/json");
			User user = gson.fromJson(req.body(), User.class);
			userService.changeUser(user);
			return "OK";
		});
	}
	
	public static void CheckUserPassword() {
		get("user/checkPassword",(req, res) -> {
			String currentPassword = req.queryParams("currentPassword");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(currentPassword.equals(userService.getUser(user.getUsername()).getPassword()))
				return true;
			return false;
		});
	}
	
	public static void IsUserLogged() {
		get("user/getlogged", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User loggeduser = ss.attribute("user");
			if(loggeduser != null) 
			return true;
			return false;
		});
	}
	
	public static void GetLoggedUser() {
		get("user/getLoggedUser", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User loggeduser = ss.attribute("user");
			if(loggeduser != null)
			return gson.toJson( userService.getUser(loggeduser.getUsername()));
			return null;
		});
		
	}
	
	public static void GetUserType() {
		get("user/userType", (req, res) -> {
			Session ss = req.session(true);
			User loggedUser = ss.attribute("user");
			if(loggedUser != null)
			return userService.getUser(loggedUser.getUsername()).getUserType().toString();
			return "";
		});
	}
	
	public static void LogOff() {
		get("user/logoff", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User loggeduser = ss.attribute("user");
			if(loggeduser != null) 
				ss.invalidate();
			return "success";
		});
	}

	public static void Login() {
		get("user/login",(req, res) -> {
			res.type("application/json");
			String username = req.queryParams("username");
			String password = req.queryParams("password");
			Session ss = req.session(true);
			User loggeduser = ss.attribute("user");
			if (loggeduser == null) {
				User use = userService.findUser(username, password);
				if(use != null) {
					loggeduser = use;
					ss.attribute("user", use);
					if(use.getUserType() == UserType.Customer)  { 
						if(!membershipService.checkMembership(use.getUsername())) {
							if(membershipService.getMembership(use.getUsername()).getVisitedSportArena() >
									membershipService.getMembership(use.getUsername()).getWorkoutNumber()/3) {
								if(membershipService.getMembership(use.getUsername()).getId().equals("cheap")) {
									customerFileStorage.getCustomer(use.getUsername()).setPoints(
											customerFileStorage.getCustomer(use.getUsername()).getPoints() + (5*117)/1000 * membershipService.getMembership(use.getUsername()).getVisitedSportArena());
								}
								else if(membershipService.getMembership(use.getUsername()).getId().equals("expensive")) {
									customerFileStorage.getCustomer(use.getUsername()).setPoints(
											customerFileStorage.getCustomer(use.getUsername()).getPoints() + (20*117)/1000 * membershipService.getMembership(use.getUsername()).getVisitedSportArena());
								}
								else {
									customerFileStorage.getCustomer(use.getUsername()).setPoints(
											customerFileStorage.getCustomer(use.getUsername()).getPoints() + (50*117)/1000 * membershipService.getMembership(use.getUsername()).getVisitedSportArena());
								}
								
							}

							else {
								if(membershipService.getMembership(use.getUsername()).getId().equals("cheap")) {
									customerFileStorage.getCustomer(use.getUsername()).setPoints(
											customerFileStorage.getCustomer(use.getUsername()).getPoints() + 
											(5*117)/1000 * membershipService.getMembership(use.getUsername()).getVisitedSportArena() - (5*117)/1000 * 113 * 4);
								}
								else if(membershipService.getMembership(use.getUsername()).getId().equals("expensive")) {
									customerFileStorage.getCustomer(use.getUsername()).setPoints(
											customerFileStorage.getCustomer(use.getUsername()).getPoints() + 
											(20*117)/1000 * membershipService.getMembership(use.getUsername()).getVisitedSportArena() - (20*117)/1000 * 113 * 4);
								}
								else {
									customerFileStorage.getCustomer(use.getUsername()).setPoints(
											customerFileStorage.getCustomer(use.getUsername()).getPoints() + 
											(50*117)/1000 * membershipService.getMembership(use.getUsername()).getVisitedSportArena() - (50*117)/1000 * 113 * 4);
								}
							}
						}
					}
					return "logged";
				}
				return "wrong";
			}
			return loggeduser.getUsername();
		});
	}
	
	public static void GetLoggedUsername() {
		get("user/getUsername",(req, res) -> {
			Session ss = req.session(true);
			User loggedUser = ss.attribute("user");
			return loggedUser.getUsername();
		});
	}
}
