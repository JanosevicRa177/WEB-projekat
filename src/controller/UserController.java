package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.User;
import services.UserService;
import spark.Session;

public class UserController {
	
	public static Gson gson;
	
	private static UserService userService;
	
	public UserController() {
		userService = new UserService();
		gson = new GsonBuilder()
		        .setPrettyPrinting()
		        .setDateFormat("yyyy-MM-dd")
		        .create();
	}
	
	public static void changeUser() {
		post("user/changeUser",(req, res) -> {
			res.type("application/json");
			User ut = gson.fromJson(req.body(), User.class);
			userService.changeUser(ut);
			return "OK";
		});
		
		post("user/checkPassword",(req, res) -> {
			res.type("application/json");
			JsonObject jsonObject = new JsonParser().parse(req.body()).getAsJsonObject();
			String currPassword = jsonObject.get("pass").toString().substring(1, jsonObject.get("pass").toString().length()-1);
			Session ss = req.session(true);
			User u = ss.attribute("user");
			if(currPassword.equals(userService.getUser(u.getUsername()).getPassword()))
				return true;
			return false;
		});
		
	}
	
	public static void getLogged() {
		get("user/getlogged", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User loggeduser = ss.attribute("user");
			if(loggeduser != null) 
			return true;
			return false;
		});
		
		
		get("user/getLoggedUser", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User loggeduser = ss.attribute("user");
			if(loggeduser != null)
			return gson.toJson( userService.getUser(loggeduser.getUsername()));
			return null;
		});
		
		get("user/userType", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User loggeduser = ss.attribute("user");
			if(loggeduser != null)
			return gson.toJson( userService.getUser(loggeduser.getUsername()).getUserType().toString());
			return null;
		});
		
	}
	
	public static void logOff() {
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
		post("user/login",(req, res) -> {
			res.type("application/json");
			User ut = gson.fromJson(req.body(), User.class);
			Session ss = req.session(true);
			User loggeduser = ss.attribute("user");
			if (loggeduser == null) {
				User use = userService.findUser(ut);
				if(use != null) {
					loggeduser = use;
					ss.attribute("user", use);
					return "logged";
				}
				return "wrong";
			}
			return loggeduser.getUsername();
		});
	}
}
