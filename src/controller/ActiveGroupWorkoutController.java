package controller;

import static spark.Spark.post;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.UserType;
import model.User;
import model.Workout;
import services.ActiveGroupWorkoutService;
import spark.Session;

public class ActiveGroupWorkoutController {
	public static Gson gson;
	private static ActiveGroupWorkoutService activeGroupWorkoutService;
	
	
	public ActiveGroupWorkoutController() {
		activeGroupWorkoutService = new ActiveGroupWorkoutService();
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	}
	
	public static void AddActiveGroupWorkout() {
		post("/activeGroupWorkout/add",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Coach)
				return "You are not Coach!";
			String dateString = req.queryParams("date");
			String hoursString = req.queryParams("hour");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date = formatter.parse(dateString);
			ZoneId defaultZoneId = ZoneId.systemDefault();
			Date dateTodayPlus2 = Date.from(LocalDate.now().plusDays(2).atStartOfDay(defaultZoneId).toInstant());
			int hours;
			if(date.compareTo(dateTodayPlus2) <= 0) {
				return "Date not valid!";
			}
		    try {
		        hours = Integer.parseInt(hoursString);
		    } catch (NumberFormatException nfe) {
		        return "Hours input is not number!";
		    }
		    Workout workout = gson.fromJson(req.body(), Workout.class);
			if(!workout.getCoachUsername().equals(user.getUsername())) {
				return "This is not your group workout so you cant check it!";
			}
			return activeGroupWorkoutService.AddActiveGroupWorkout(date,hours,workout);
		});
	}
}
