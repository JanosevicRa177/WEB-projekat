package controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.delete;
import static spark.Spark.put;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.UserType;
import enums.WorkoutType;
import model.User;
import model.Workout;
import services.CoachService;
import services.ManagerService;
import services.SportBuildingService;
import services.WorkoutService;
import spark.Session;

public class WorkoutController {
	public static Gson gson;
	private static WorkoutService workoutService;
	private static CoachService coachService;
	private static SportBuildingService sportBuildingService;
	private static ManagerService managerService;
	
	public WorkoutController() {
		workoutService = new WorkoutService();
		coachService = new CoachService();
		sportBuildingService = new SportBuildingService();
		managerService = new ManagerService();
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	}
	
	public static void getCoachesforSportBuilding() {
		get("workout/getCoaches",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			String spB = managerService.getManager(user.getUsername()).getSportBuilding();
			return gson.toJson(workoutService.getAllCoachesForSportBuilding(spB));
		});
	}
	
	public static void AddWorkout() {
		post("/workout/add",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Manager)
				return "You are not Manager!";
			Workout workout = gson.fromJson(req.body(), Workout.class);
			if(!workoutService.IsUniqueName(workout.getName())){
				return "Name is not unique";
			}
			if(workout.getType() == WorkoutType.Group || workout.getType() == WorkoutType.Personal)
			{
				if(!coachService.IsValidCoach(workout.getCoachUsername())) {
					return "This coach does not exist!";
				}
			}
			if(!workout.getCoachUsername().equals("None") && (workout.getType() == WorkoutType.Gym || workout.getType() == WorkoutType.Sauna)) {
				return "Gym trainings and saunas does not require coaches";
			}
			String sportBuilding = sportBuildingService.GetSportBuildingNameByManager(user.getUsername());
			workout.setSportBuildingName(sportBuilding);
			return workoutService.addWorkout(workout);
		});
	}
	
	public static void ChangeWorkout() {
		put("/workout/change",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Manager)
				return "You are not Manager!";
			Workout workout = gson.fromJson(req.body(), Workout.class);
			if(!workout.getName().equals(ss.attribute("changingWorkout"))) {
				if(!workoutService.IsUniqueName(workout.getName())){
					return "Name is not unique";
				}
			}
			if(workout.getType() == WorkoutType.Group || workout.getType() == WorkoutType.Personal)
			{
				if(!coachService.IsValidCoach(workout.getCoachUsername())) {
					return "This coach does not exist!";
				}
			}
			if(!workout.getCoachUsername().equals("None") && (workout.getType() == WorkoutType.Gym || workout.getType() == WorkoutType.Sauna)) {
				return "Gym trainings and saunas does not require coaches";
			}
			String sportBuilding = sportBuildingService.GetSportBuildingNameByManager(user.getUsername());
			workout.setSportBuildingName(sportBuilding);
			return workoutService.ChangeWorkout(workout,ss.attribute("changingWorkout"));
		});
	}
	
	public static void GetWorkoutsByManager() {
		get("/workout/getAllByManager",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Manager)
				return "You are not Manager!";
			String sportBuilding = sportBuildingService.GetSportBuildingNameByManager(user.getUsername());
			return gson.toJson(workoutService.GetWorkoutsBySportBuilding(sportBuilding));
		});
	}
	
	public static void GetWorkoutsByCoach() {
		get("/workout/getAllByCoach",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Coach)
				return "You are not Coach!";
			return gson.toJson(workoutService.GetWorkoutsByCoach(user.getUsername()));
		});
	}
	
	public static void GetWorkoutsByCustomer() {
		get("/workout/getAllByCustomer",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Customer)
				return "You are not Customer!";
			return gson.toJson(workoutService.GetPersonalWorkouts());
		});
	}
	
	
	public static void GetWorkoutByName() {
		get("/workout/getByName",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Manager)
				return "You are not Manager!";
			String workoutName = req.queryParams("ContentName");
			ss.attribute("changingWorkout", workoutName);
			return gson.toJson(workoutService.GetWorkoutsByName(workoutName));
		});
	}
	
	public static void InvalidateChangingWorkout() {
		delete("/workout/invalidateChange",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			ss.removeAttribute("changingWorkout");
			return "Success!";
		});
	}
}
