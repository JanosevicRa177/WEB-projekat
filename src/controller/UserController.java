package controller;

import static spark.Spark.get;
import static spark.Spark.put;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.MembershipStatus;
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
			return "";
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
						if(!membershipService.checkMembership(use.getUsername()) && membershipService.getMembership(use.getUsername()).getStatus() == MembershipStatus.Active) {
							if(membershipService.getMembership(use.getUsername()).getVisitedSportArena() >
									membershipService.getMembership(use.getUsername()).getWorkoutNumber()/3) {
								if(membershipService.getMembership(use.getUsername()).getId().equals("cheap")) {
									customerFileStorage.setPoints(use.getUsername(),
											customerFileStorage.getCustomer(use.getUsername()).getPoints() + (5.0*117.0)/1000.0 * membershipService.getMembership(use.getUsername()).getVisitedSportArena());
								}
								else if(membershipService.getMembership(use.getUsername()).getId().equals("expensive")) {
									customerFileStorage.setPoints(use.getUsername(),
											customerFileStorage.getCustomer(use.getUsername()).getPoints() + (20.0*117.0)/1000.0 * membershipService.getMembership(use.getUsername()).getVisitedSportArena());
								}
								else {
									customerFileStorage.setPoints(use.getUsername(),
											customerFileStorage.getCustomer(use.getUsername()).getPoints() + (50.0*117.0)/1000.0 * membershipService.getMembership(use.getUsername()).getVisitedSportArena());
								}
								
							}

							else {
								if(membershipService.getMembership(use.getUsername()).getId().equals("cheap")) {
									customerFileStorage.setPoints(use.getUsername(),
											customerFileStorage.getCustomer(use.getUsername()).getPoints() + 
											(5*117)/1000 * membershipService.getMembership(use.getUsername()).getVisitedSportArena() - (5.0*117.0)/1000.0 * 113.0 * 4.0);
								}
								else if(membershipService.getMembership(use.getUsername()).getId().equals("expensive")) {
									customerFileStorage.setPoints(use.getUsername(),
											customerFileStorage.getCustomer(use.getUsername()).getPoints() + 
											(20*117)/1000 * membershipService.getMembership(use.getUsername()).getVisitedSportArena() - (20.0*117.0)/1000.0 * 113.0* 4.0);
								}
								else {
									customerFileStorage.setPoints(use.getUsername(),
											customerFileStorage.getCustomer(use.getUsername()).getPoints() + 
											(50*117)/1000 * membershipService.getMembership(use.getUsername()).getVisitedSportArena() - (50.0*117.0)/1000.0* 113.0 * 4.0);
								}
							}
							membershipService.inactive(use.getUsername());
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
