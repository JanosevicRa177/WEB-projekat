package controller;

import static spark.Spark.post;
import static spark.Spark.get;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import DTOs.WorkoutHistoryDTO;
import enums.UserType;
import model.SportBuilding;
import model.User;
import model.Workout;
import model.WorkoutHistory;
import services.SportBuildingService;
import services.WorkoutHistoryService;
import services.WorkoutService;
import spark.Session;

public class WorkoutHistoryController {
	public static Gson gson;
	private static WorkoutService workoutService;
	private static WorkoutHistoryService workoutHistoryService;
	private static SportBuildingService sportBuildingService;
	
	public WorkoutHistoryController() {
		workoutService = new WorkoutService();
		workoutHistoryService = new WorkoutHistoryService();
		sportBuildingService = new SportBuildingService();
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	}
	
	public static void AddToWorkoutHistoryPersonal() {
		post("/workoutHistory/add",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Customer)
				return "You are not Customer!";
			String workoutName = req.queryParams("workoutName");
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
			Workout workout = workoutService.GetWorkoutsByName(workoutName);
			if(workout == null) {
				return "Workout does not exists!";
			}
			return workoutHistoryService.AddWorkoutHistory(date,hours,workout,user.getUsername());
		});
	}
	
	public static void AddToWorkoutHistoryGroup() {
		post("/workoutHistory/addGroup",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Customer)
				return "You are not Customer!";
			String workoutName = req.queryParams("workoutName");
			String dateString = req.queryParams("date");
			String hoursString = req.queryParams("hour");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date = formatter.parse(dateString);
			ZoneId defaultZoneId = ZoneId.systemDefault();
			Date dateTodayPlus1 = Date.from(LocalDate.now().plusDays(1).atStartOfDay(defaultZoneId).toInstant());
			int hours;
			if(date.compareTo(dateTodayPlus1) <= 0) {
				return "Date not valid!";
			}
		    try {
		        hours = Integer.parseInt(hoursString);
		    } catch (NumberFormatException nfe) {
		        return "Hours input is not number!";
		    }
			Workout workout = workoutService.GetWorkoutsByName(workoutName);
			if(workout == null) {
				return "Workout does not exists!";
			}
			return workoutHistoryService.AddWorkoutHistory(date,hours,workout,user.getUsername());
		});
	}
	public static void GetWorkoutHistoryCustomer() {
		get("/workoutHistory/getAllByCustomer",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Customer)
				return "You are not Customer!";
			
			Map<String, Workout> workouts = workoutService.GetAllWorkouts();
			Map<String, SportBuilding> sportBuildings = sportBuildingService.GetSportBuildingsMap();
			Collection<WorkoutHistory> customerWorkoutHistory = workoutHistoryService.GetWorkoutHistoryByCustomer(user.getUsername());
			Collection<WorkoutHistoryDTO> workoutsDTOS = new HashSet<WorkoutHistoryDTO>();
			
			for(WorkoutHistory WorkoutHistory : customerWorkoutHistory) {
				Workout workout = workouts.get(WorkoutHistory.getWorkout());
				SportBuilding sportbuilding = sportBuildings.get(workout.getSportBuildingName());
				WorkoutHistoryDTO workoutDTO = new WorkoutHistoryDTO(WorkoutHistory.getCheckinDate(),WorkoutHistory.getHours(),
						WorkoutHistory.getWorkout(),workout.getType(),workout.getSportBuildingName(),workout.getPrice(),sportbuilding.getType());
				workoutsDTOS.add(workoutDTO);
			}
			return gson.toJson(workoutsDTOS);
		});
	}
	public static void GetWorkoutHistoryCoach() {
		get("/workoutHistory/getAllByCoach",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Coach)
				return "You are not Coach!";
			
			Map<String, Workout> workouts = workoutService.GetAllWorkouts();
			Map<String, SportBuilding> sportBuildings = sportBuildingService.GetSportBuildingsMap();
			Collection<WorkoutHistory> customerWorkoutHistory = workoutHistoryService.GetWorkoutHistoryByCoach(user.getUsername());
			Collection<WorkoutHistoryDTO> workoutsDTOS = new HashSet<WorkoutHistoryDTO>();
			
			for(WorkoutHistory WorkoutHistory : customerWorkoutHistory) {
				Workout workout = workouts.get(WorkoutHistory.getWorkout());
				SportBuilding sportbuilding = sportBuildings.get(workout.getSportBuildingName());
				WorkoutHistoryDTO workoutDTO = new WorkoutHistoryDTO(WorkoutHistory.getCheckinDate(),WorkoutHistory.getHours(),
						WorkoutHistory.getWorkout(),workout.getType(),workout.getSportBuildingName(),workout.getPrice(),sportbuilding.getType());
				workoutsDTOS.add(workoutDTO);
			}
			return gson.toJson(workoutsDTOS);
		});
	}
	public static void GetWorkoutHistoryManager() {
		get("/workoutHistory/getAllByManager",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Manager)
				return "You are not Manager!";
			
			Map<String, Workout> workouts = workoutService.GetAllWorkouts();
			Map<String, SportBuilding> sportBuildings = sportBuildingService.GetSportBuildingsMap();
			String sportBuilding = "";
			
			for(SportBuilding sportBuildingTemp : sportBuildings.values()) {
				if(sportBuildingTemp.getManager().equals(user.getUsername())) {
					sportBuilding = sportBuildingTemp.getName();
					break;
				}
			}
			Collection<WorkoutHistory> customerWorkoutHistory = workoutHistoryService.GetWorkoutHistory();
			Collection<WorkoutHistoryDTO> workoutsDTOS = new HashSet<WorkoutHistoryDTO>();
			
			for(WorkoutHistory WorkoutHistory : customerWorkoutHistory) {
				Workout workout = workouts.get(WorkoutHistory.getWorkout());
				if(!workout.getSportBuildingName().equals(sportBuilding)) {
					continue;
				}
				SportBuilding sportbuilding = sportBuildings.get(workout.getSportBuildingName());
				WorkoutHistoryDTO workoutDTO = new WorkoutHistoryDTO(WorkoutHistory.getCheckinDate(),WorkoutHistory.getHours(),
						WorkoutHistory.getWorkout(),workout.getType(),workout.getSportBuildingName(),workout.getPrice(),sportbuilding.getType());
				workoutsDTOS.add(workoutDTO);
			}
			return gson.toJson(workoutsDTOS);
		});
	}
}
