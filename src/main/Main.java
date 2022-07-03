package main;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;

import controller.AdminController;
import controller.CoachController;
import controller.CustomerController;
import controller.ManagerController;
import controller.SportBuildingController;
import controller.UserController;
import controller.WorkoutController;

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
		
		CustomerController.addCustomer();
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
		
		CustomerController.GetAllCustomers();
		
		ManagerController.GetAllManagers();
		ManagerController.addManager();
		ManagerController.CheckSportBuilding();
		
		CoachController.GetAllCoaches();
		CoachController.addCoach();
		
		AdminController.GetAllAdmins();
		
		WorkoutController.AddWorkout();
	}

}
