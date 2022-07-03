package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Customer;
import model.SportBuilding;
import services.SportBuildingService;

public class SportBuildingController {
	public static Gson gson;
	private static SportBuildingService sportBuildingService;
	public SportBuildingController() {
		sportBuildingService = new SportBuildingService();
		gson = new GsonBuilder()
		        .create();
	}
	public static void GetSportBuildings() {
		get("sportBuilding/getAll", (req, res) -> {
			res.type("application/json");
			return gson.toJson(sportBuildingService.getSportBuildings());
		});
	}
	public static void GetSportBuilding() {
		get("sportBuilding/get", (req, res) -> {
			res.type("application/json");
			String sportBuildingName = req.queryParams("sportBuildingName");
			return gson.toJson(sportBuildingService.GetSportBuilding(sportBuildingName));
		});
	}
	
	public static void addSportBuilding() {
		post("sportBuilding/add",(req, res) -> {
			res.type("application/json");
			SportBuilding spB = gson.fromJson(req.body(), SportBuilding.class);
			if(!spB.getName().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$") || !spB.getLocation().getAddress().getCity().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$") || !spB.getLocation().getAddress().getStreet().matches("^[A-Z.-]+(\\s*[A-Za-z.-]+)*$"))
				return "Name,city and street should start with uppercase without numbers";
			if(!spB.getLocation().getAddress().getNumber().matches("^(\s*[A-Za-z0-9.-]+)*$"))
				return "Number cannot contain any special characters";
			if(!spB.getLocation().getAddress().getZipCode().matches("^\\d+$"))
				return "Zip Code can contain only numbers!";
			if(!sportBuildingService.isNameUnique(spB.getName()))
				return "Name is not unique";
			sportBuildingService.addSportBuilding(spB);
			return "success";
		});
	}
	
	public static void GetSportBuildingsNoManager() {
		get("sportBuilding/getAllNoManager", (req, res) -> {
			res.type("application/json");
			return gson.toJson(sportBuildingService.getSportBuildingsNoManager());
		});
	}
}
