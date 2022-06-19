package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	
	public static void getLogged() {
		get("user/getlogged", (req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User loggeduser = ss.attribute("user");
			if(loggeduser != null) 
			return true;
			return false;
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
			System.out.println(req.cookie("logincookie"));
			if (loggeduser == null) {
				User use = userService.loginUser(ut);
				if(use != null) {
					loggeduser = use;
					ss.attribute("user", use);
					if(req.cookie("logincookie") == null) {
						String randCook = "";
						for (int i =0; i<50;i++) randCook += Math.round(Math.random()*10);
						res.cookie("logincookie", randCook); 
						
					}
					return "logged";
				}
				return "wrong";
			}
			return loggeduser.getUsername();
		});
	}
}
