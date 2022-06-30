package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.UserType;
import model.Coach;
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
	
	public static void addCoach() {
		post("coach/add",(req, res) -> {
			res.type("application/json");
			Coach coach = gson.fromJson(req.body(), Coach.class);
			if(!coach.getName().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$") || !coach.getSurname().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$"))
				return "Name and surname should start with uppercase without numbers";
			if(!coachService.isUniqueUsername(coach.getUsername()) || !coachService.isUniqueUsername(coach.getUsername())
					|| !coachService.isUniqueUsername(coach.getUsername()) || !coachService.isUniqueUsername(coach.getUsername()))
				return "Username is not unique";
			return coachService.addCoach(coach);
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
