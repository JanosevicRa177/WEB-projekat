package controller;

import static spark.Spark.get;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.UserType;
import model.User;
import services.CoachService;
import spark.Session;

public class CoachController {
	public static Gson gson;
	private static CoachService coachService;
	public CoachController() {
		coachService = new CoachService();
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	}
	
	public static void getCoach() {
		get("customer/get", (req, res) -> {
			res.type("application/json");
			return "SUCCESS";
		});
	}
	
	public static void GetAllCoaches() {
		get("coach/getAll",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user.getUserType() == UserType.Admin)
				return gson.toJson(coachService.GetAllCoaches());
			else return null;
		});
	}
}
