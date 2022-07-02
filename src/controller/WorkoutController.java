package controller;

import static spark.Spark.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.UserType;
import model.User;
import model.Workout;
import services.WorkoutService;
import spark.Session;

public class WorkoutController {
	public static Gson gson;
	private static WorkoutService workoutService;
	
	public WorkoutController() {
		workoutService = new WorkoutService();
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	}
	
	public static void addWorkout() {
		post("coach/add",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Manager)
				return "You are not Manager!";
			Workout workout = gson.fromJson(req.body(), Workout.class);
			if(!workoutService.isUniqueName(workout.getName())){
				return "Name is not unique";
			}
			return workoutService.addWorkout(workout);
		});
	}
	
}
