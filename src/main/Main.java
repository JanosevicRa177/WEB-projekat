package main;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.File;
import java.io.IOException;

import controller.CustomerController;
import controller.SportBuildingController;
import controller.UserController;

public class Main {

	public static void main(String[] args) throws IOException {
		port(8090);
		
		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		new CustomerController();
		new SportBuildingController();
		new UserController();
		CustomerController.addCustomer();
		CustomerController.getCustomer();
		UserController.Login();
		UserController.getLogged();
		UserController.logOff();
		UserController.changeUser();
		SportBuildingController.getSportBuildings();
	}

}
