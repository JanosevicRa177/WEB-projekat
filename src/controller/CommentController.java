package controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import enums.UserType;
import model.SportBuilding;
import model.User;
import model.Workout;
import model.WorkoutHistory;
import services.CommentService;
import services.SportBuildingService;
import services.WorkoutHistoryService;
import services.WorkoutService;
import spark.Session;

public class CommentController {
	public static Gson gson;
	private static CommentService commentService;
	private static SportBuildingService sportBuildingService;
	private static WorkoutService workoutService;
	private static WorkoutHistoryService workoutHistoryService;
	
	public CommentController() {
		commentService = new CommentService();
		sportBuildingService = new SportBuildingService();
		workoutService = new WorkoutService();
		workoutHistoryService = new WorkoutHistoryService();
		gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	}
	
	public static void CheckSportBuildingCommentByCustomer(){
		get("/comment/checkCustomer",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return true;
			if(user.getUserType() != UserType.Customer)
				return true;
			String SportBuildingName = req.queryParams("sportBuildingName");
			SportBuilding sportBuilding = sportBuildingService.GetSportBuilding(SportBuildingName);
			if(sportBuilding == null) {
				return true;
			}
			
			Map<String, Workout> workouts = workoutService.GetAllWorkouts();
			Collection<WorkoutHistory> customerWorkoutHistory = workoutHistoryService.GetWorkoutHistoryByCustomer(user.getUsername());
			ZoneId defaultZoneId = ZoneId.systemDefault();
			Date dateToday = Date.from(LocalDate.now().atStartOfDay(defaultZoneId).toInstant());
			boolean didGoToSportBuilding = false;
			for(WorkoutHistory workoutHistory : customerWorkoutHistory) {
				if(workouts.get(workoutHistory.getWorkout()).getSportBuildingName().equals(SportBuildingName) && (workoutHistory.getCheckinDate().compareTo(dateToday) < 0)) {
					didGoToSportBuilding = true;
				}
			}
			if(!didGoToSportBuilding) {
				return true;
			}
			return commentService.DidCustomerCommentSportBuilding(user.getUsername(), SportBuildingName);
		});
	}
	public static void GetCommentsBySportBuilding(){
		get("/comment/getCommentsBySportBuilding",(req, res) -> {
			res.type("application/json");
			String SportBuildingName = req.queryParams("sportBuildingName");
			SportBuilding sportBuilding = sportBuildingService.GetSportBuilding(SportBuildingName);
			if(sportBuilding == null) {
				return "Sport building does not exists!";
			}
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null) {
				return gson.toJson(commentService.GetCommentsBySportBuildingForEveryone(SportBuildingName));
			} else {
				if(user.getUserType() == UserType.Admin)
					return gson.toJson(commentService.GetCommentsBySportBuildingForAdminAndManager(SportBuildingName));
				else if (user.getUserType() == UserType.Manager)
					if(sportBuilding.getManager().equals(user.getUsername())) {
						return gson.toJson(commentService.GetCommentsBySportBuildingForAdminAndManager(SportBuildingName));
					}
			}
			return gson.toJson(commentService.GetCommentsBySportBuildingForEveryone(SportBuildingName));
		});
	}
	public static void CommentSportBuildingByCustomer(){
		post("/comment/comment",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Customer)
				return "You are not Customer!";
			String SportBuildingName = req.queryParams("sportBuildingName");
			String gradeString = req.queryParams("Grade");
			String comment = req.queryParams("Comment");
			int grade;
		    try {
		    	grade = Integer.parseInt(gradeString);
		    } catch (NumberFormatException nfe) {
		        return "Grade input is not number!";
		    }
		    if(grade > 10 || grade < 0) {
		    	return "Grade not valid!";
		    }
		    if(comment.equals("")) {
		    	return "comment not Valid!";
		    }
			SportBuilding sportBuilding = sportBuildingService.GetSportBuilding(SportBuildingName);
			if(sportBuilding == null) {
				return "Sport building does not exists!";
			}
			return commentService.AddComment(user.getUsername(), SportBuildingName, comment, grade);
		});
	}
	public static void ApproveComment(){
		put("/comment/approveComment",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Admin)
				return "You are not Admin!";
			String idString = req.queryParams("Id");
			int id;
		    try {
		    	id = Integer.parseInt(idString);
		    } catch (NumberFormatException nfe) {
		        return "Sent id is not valid!";
		    }
			return commentService.ApproveComment(idString);
		});
	}
	public static void DenyComment(){
		put("/comment/denyComment",(req, res) -> {
			res.type("application/json");
			Session ss = req.session(true);
			User user = ss.attribute("user");
			if(user == null)
				return "You are not logged in!";
			if(user.getUserType() != UserType.Admin)
				return "You are not Admin!";
			String idString = req.queryParams("Id");
			int id;
		    try {
		    	id = Integer.parseInt(idString);
		    } catch (NumberFormatException nfe) {
		        return "Sent id is not valid!";
		    }
			return commentService.DenyComment(idString);
		});
	}
}
