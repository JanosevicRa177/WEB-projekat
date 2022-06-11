package controller;

import static spark.Spark.get;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import services.CoachService;

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
}
