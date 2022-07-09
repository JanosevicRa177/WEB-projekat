package main;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;

import controller.ActiveGroupWorkoutController;
import controller.AdminController;
import controller.CoachController;
import controller.CustomerController;
import controller.ManagerController;
import controller.MembershipController;
import controller.SportBuildingController;
import controller.UserController;
import controller.WorkoutController;
import controller.WorkoutHistoryController;

public class Main {

	public static void main(String[] args) throws IOException {
		port(8090);
		
		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		
		new CustomerController();
		new ManagerController();
		new AdminController();
		new CoachController();
		new SportBuildingController();
		new UserController();
		new WorkoutController();
		new WorkoutHistoryController();
		new ActiveGroupWorkoutController();
		new MembershipController();
		
		CustomerController.addCustomer();
		CustomerController.GetAllCustomers();
		CustomerController.getCustomersSportBuilding();
		
		UserController.Login();
		UserController.GetLoggedUser();
		UserController.IsUserLogged();
		UserController.GetUserType();
		UserController.LogOff();
		UserController.ChangeUser();
		UserController.CheckUserPassword();
		UserController.GetLoggedUsername();
		
		SportBuildingController.GetSportBuildings();
		SportBuildingController.GetSportBuildingsNoManager();
		SportBuildingController.GetSportBuilding();
		SportBuildingController.addSportBuilding();
		
		ManagerController.GetAllManagers();
		ManagerController.getManagersSportBuilding();
		ManagerController.addManager();
		ManagerController.CheckSportBuilding();
		ManagerController.getAllNoSportBuilding();
		
		CoachController.GetAllCoaches();
		CoachController.addCoach();
		
		AdminController.GetAllAdmins();
		
		WorkoutController.AddWorkout();
		WorkoutController.getCoachesforSportBuilding();
		WorkoutController.ChangeWorkout();
		WorkoutController.GetWorkoutsByManager();
		WorkoutController.GetWorkoutsByCustomer();
		WorkoutController.GetWorkoutsByCoach();
		WorkoutController.GetWorkoutByName();
		WorkoutController.InvalidateChangingWorkout();
		
		WorkoutHistoryController.AddToWorkoutHistoryPersonal();
		WorkoutHistoryController.AddToWorkoutHistoryGroup();
		WorkoutHistoryController.GetWorkoutHistoryCustomer();
		WorkoutHistoryController.GetWorkoutHistoryManager();
		WorkoutHistoryController.GetWorkoutHistoryCoach();
		
		ActiveGroupWorkoutController.AddActiveGroupWorkout();
		ActiveGroupWorkoutController.GetActiveGroupWorkoutsByCustomer();
		
		MembershipController.createMembership();
		MembershipController.getMemebership();
	}

}
