package controller;

import static spark.Spark.get;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	
	public static void GetSportBuildingsNoManager() {
		get("sportBuilding/getAllNoManager", (req, res) -> {
			res.type("application/json");
			return gson.toJson(sportBuildingService.getSportBuildingsNoManager());
		});
	}
}
